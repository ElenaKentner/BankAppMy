package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.entity.enums.Status;
import com.example.bankapp.mapper.AccountMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientService clientService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, ClientService clientService) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.clientService = clientService;
    }

    @Transactional
    @Override
    public Account createAccount(AccountDTO accountDTO) {
        Account account = accountMapper.mapToEntity(accountDTO);

        Client client = clientService.getClientById(accountDTO.getClientId());

        account.setClient(client);
        account.setStatus(Status.NEW);
        account.setBalance(BigDecimal.ZERO);
        account.setName(RandomStringUtils.randomNumeric(12));
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());

        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }

    @Transactional
    @Override
    public AccountDTO findAccountById(String id) {
        Account account = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return accountMapper.mapToDto(account);
    }

    @Transactional
    @Override
    public AccountDTO updateAccount(String id, AccountDTO updatedAccountDTO) {
        Account existingAccount = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        if (updatedAccountDTO.getClientId() != null &&
                !updatedAccountDTO.getClientId().equals(existingAccount.getClient().getId().toString())){
            Client client = clientService.getClientById(updatedAccountDTO.getClientId());
            existingAccount.setClient(client);
        }
        accountMapper.updateAccountFromDTO(updatedAccountDTO, existingAccount);
        Account updatedAccount = accountRepository.save(existingAccount);
        return accountMapper.mapToDto(updatedAccount);
    }

    @Transactional
    @Override
    public List<AccountDTO> getByProductName(String productName) {
        List<Account> accountList = accountRepository.getByProductName(productName);
        return accountMapper.mapToDtoList(accountList);
    }

}

