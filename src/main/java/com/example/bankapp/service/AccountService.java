package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account createAccount(AccountDTO accountDTO);

    void deleteAccount(UUID accountId);

    AccountDTO findAccountById(String id);

    AccountDTO updateAccount(String id, AccountDTO updatedAccountDTO);

    List<AccountDTO> getByProductName(String productName);

}
