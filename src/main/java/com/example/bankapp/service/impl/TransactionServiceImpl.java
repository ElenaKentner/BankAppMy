package com.example.bankapp.service.impl;

import com.example.bankapp.dto.TransactionDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.entity.enums.Status;
import com.example.bankapp.exception.CurrencyCheckException;
import com.example.bankapp.exception.InvalidAmountException;
import com.example.bankapp.exception.NotEnoughMoneyException;
import com.example.bankapp.mapper.TransactionMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void deleteTransactionById(UUID id) {
        transactionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TransactionDTO findTransactionById(String id) {
        Transaction transaction = transactionRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
        return transactionMapper.mapToDto(transaction);
    }

    @Override
    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Account debitAccount = accountRepository.findByName(transactionDTO.getDebitAccountName())
                .orElseThrow(() -> new EntityNotFoundException("account not found"));
        Account creditAccount = accountRepository.findByName(transactionDTO.getCreditAccountName())
                .orElseThrow(() -> new EntityNotFoundException("account not found"));

        checkValidation(transactionDTO, debitAccount, creditAccount);

        Transaction transaction = transactionMapper.mapToEntity(transactionDTO);

        debitAccount.setBalance(debitAccount.getBalance().subtract(transaction.getAmount()));
        creditAccount.setBalance(creditAccount.getBalance().add(transaction.getAmount()));
        debitAccount.setUpdatedAt(LocalDateTime.now());
        creditAccount.setUpdatedAt(LocalDateTime.now());
        transaction.setStatus(Status.SUCCESS);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setDebitAccount(debitAccount);
        transaction.setCreditAccount(creditAccount);

        accountRepository.save(debitAccount);
        accountRepository.save(creditAccount);
        transactionRepository.save(transaction);

        return transactionMapper.mapToDto(transaction);
    }

    private static void checkValidation(TransactionDTO transactionDTO, Account debitAccount, Account creditAccount) {
        Double amountDouble = null;
        try {
            amountDouble = Double.parseDouble(transactionDTO.getAmount());
        } catch (NumberFormatException exception) {
            throw new InvalidAmountException("amount must contain only numbers");
        }
        if (amountDouble <= 0) {
            throw new InvalidAmountException("amount must be positive");
        }
        if (debitAccount.getBalance().compareTo(new BigDecimal(amountDouble)) < 0) {
            throw new NotEnoughMoneyException("you do not have enough funds to transfer");
        }
        if (debitAccount.getCurrencyCode() != creditAccount.getCurrencyCode()) {
            throw new CurrencyCheckException("a transaction can only be made with one currency");
        }
    }
}
