package com.example.bankapp.impl;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(AccountDTO accountDTO) {
        Account account = new Account();

        Client client = new Client();
        client.setId(accountDTO.getClientId());
        account.setClientId(client);

        account.setStatus(accountDTO.getStatus());
        account.setCurrencyCode(accountDTO.getCurrencyCode());
        account.setType(accountDTO.getType());
        account.setBalance(accountDTO.getBalance());
        account.setName(accountDTO.getName());
        account.setCreatedAt(accountDTO.getCreatedAt());
        account.setUpdatedAt(accountDTO.getUpdatedAt());

        return accountRepository.save(account);
    }

}

