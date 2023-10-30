package com.example.bankapp.controller;

import com.example.bankapp.dto.TransactionDTO;
import com.example.bankapp.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findTransaction(@PathVariable String id){
        TransactionDTO transactionDTO = transactionService.findTransactionById(id);
        return ResponseEntity.ok(transactionDTO);
    }
}
