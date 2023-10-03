package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import org.springframework.stereotype.Service;


@Service
public interface AccountService {
    Account createAccount(AccountDTO accountDTO);
}
