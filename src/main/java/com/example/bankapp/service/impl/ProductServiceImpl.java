package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ProductDTO;
import com.example.bankapp.entity.Product;
import com.example.bankapp.entity.enums.Status;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Product updateProductById(String id, ProductDTO updatedProduct) {
        Product existingProduct = productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException(id + " not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setStatus(Status.valueOf(updatedProduct.getStatus()));
        existingProduct.setInterestRate(new BigDecimal(updatedProduct.getInterestRate()));
        existingProduct.setMinLimit(Long.valueOf(updatedProduct.getMinLimit()));

        return productRepository.save(existingProduct);
    }
}



