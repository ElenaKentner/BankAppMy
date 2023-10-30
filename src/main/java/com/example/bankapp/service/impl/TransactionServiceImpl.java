package com.example.bankapp.service.impl;

import com.example.bankapp.dto.TransactionDTO;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.mapper.TransactionMapper;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public void deleteTransactionById(UUID id) {
        transactionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TransactionDTO findTransactionById(String id) {
        Transaction transaction = transactionRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new EntityNotFoundException("Transaction not found"));
        return transactionMapper.mapToDto(transaction);
    }
}
