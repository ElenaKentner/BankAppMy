package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        Account createdAccount = accountService.createAccount(accountDTO);
        return ResponseEntity.ok(createdAccount);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findAccount(@PathVariable String id) {
        AccountDTO accountDTO = accountService.findAccountById(id);
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable String id, @RequestBody AccountDTO updatedAccountDTO) {
        AccountDTO updatedAccount = accountService.updateAccount(id, updatedAccountDTO);
        return ResponseEntity.ok(updatedAccount);
    }

}

