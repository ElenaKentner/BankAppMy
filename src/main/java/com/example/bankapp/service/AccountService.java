package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AccountService {
    Account createAccount(AccountDTO accountDTO);
    void deleteAccount(UUID accountId);
    AccountDTO findAccountById(String id);
    AccountDTO updateAccount(String id, AccountDTO updatedAccountDTO);
}
