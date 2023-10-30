package com.example.bankapp.controller;

import com.example.bankapp.dto.AgreementDTO;
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
    public ResponseEntity<AgreementDTO> create(@RequestBody AgreementDTO agreementDto) {
        AgreementDTO createdAgreement = agreementService.createAgreement(agreementDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAgreement);
    }
}
