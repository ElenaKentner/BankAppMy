package com.example.bankapp.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TransactionService {
    boolean deleteTransactionById(UUID id);
}

