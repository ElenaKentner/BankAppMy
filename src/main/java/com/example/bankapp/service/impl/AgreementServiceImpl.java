package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AgreementDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Agreement;
import com.example.bankapp.entity.Product;
import com.example.bankapp.entity.enums.Status;
import com.example.bankapp.mapper.AgreementMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.AgreementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AgreementDTO createAgreement(AgreementDTO agreementDto) {
        Agreement agreement = new Agreement();
        agreement.setStatus(Status.NEW);
        agreement.setCreatedAt(LocalDateTime.now());
        agreement.setUpdatedAt(LocalDateTime.now());

        Product product = productRepository.findById(UUID.fromString(agreementDto.getProductId()))
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Account account = accountRepository.findById(UUID.fromString(agreementDto.getAccountId()))
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        agreement.setProduct(product);
        agreement.setAccount(account);

        agreementRepository.save(agreement);

        return agreementMapper.mapToDto(agreement);
    }
}
