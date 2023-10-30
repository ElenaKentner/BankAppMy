package com.example.bankapp.service;

import com.example.bankapp.dto.TransactionDTO;

import java.util.UUID;

public interface TransactionService {
    void deleteTransactionById(UUID id);

    TransactionDTO findTransactionById(String id);

}

