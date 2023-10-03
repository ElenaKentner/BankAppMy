package com.example.bankapp.controller;

import com.example.bankapp.entity.Agreement;
import com.example.bankapp.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agreements")
@RequiredArgsConstructor
public class AgreementController {
    private final AgreementService agreementService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Agreement agreement) {
        if (agreement == null) {
            return ResponseEntity.badRequest().body("Invalid request: Agreement cannot be null");
        }

        Agreement createdAgreement = agreementService.createAgreement(agreement);
        return ResponseEntity.status(HttpStatus.CREATED).body("Agreement created with ID: "
                + createdAgreement.getId());
    }
}
