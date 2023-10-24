package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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
        return ResponseEntity.created(URI.create("/" + createdAccount.getId())).body(createdAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findAccount(@PathVariable String id) {
        AccountDTO accountDTO = accountService.findAccountById(id);
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable String id, @RequestBody AccountDTO updatedAccountDTO) {
        AccountDTO updatedAccount = accountService.updateAccount(id, updatedAccountDTO);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/by-product-name")
    public ResponseEntity<List<AccountDTO>> getByProductName(@RequestParam(name = "productName") String productName){
        List<AccountDTO> accountDTOList = accountService.getByProductName(productName);
        return ResponseEntity.ok(accountDTOList);
    }

}

