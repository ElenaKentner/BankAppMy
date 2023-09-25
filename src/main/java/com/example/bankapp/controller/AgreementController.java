package com.example.bankapp.controller;

import com.example.bankapp.entity.Agreement;
import com.example.bankapp.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agreements")
@RequiredArgsConstructor
public class AgreementController {
    private final AgreementService agreementService;

    @PostMapping ("/create")
    public Agreement create(@RequestBody Agreement agreement) {
        return agreementService.createAgreement(agreement);
    }
}
