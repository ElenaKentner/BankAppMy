package com.example.bankapp.impl;

import com.example.bankapp.entity.Agreement;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository  agreementRepository;

    @Override
    public Agreement createAgreement(Agreement agreement) {
        return agreementRepository.save(agreement);
    }
}
