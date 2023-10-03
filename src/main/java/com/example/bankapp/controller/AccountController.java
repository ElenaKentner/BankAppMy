package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        Account createdAccount = accountService.createAccount(accountDTO);

        AccountDTO createdAccountDTO = new AccountDTO();
        createdAccountDTO.setId(createdAccount.getId());
        createdAccountDTO.setClientId(createdAccount.getClientId().getId());
        createdAccountDTO.setCurrencyCode(createdAccount.getCurrencyCode());
        createdAccountDTO.setType(createdAccount.getType());
        createdAccountDTO.setBalance(createdAccount.getBalance());
        createdAccountDTO.setName(createdAccount.getName());
        createdAccountDTO.setCreatedAt(createdAccount.getCreatedAt());
        createdAccountDTO.setUpdatedAt(createdAccount.getUpdatedAt());

        return ResponseEntity.ok(createdAccountDTO);
    }

}

