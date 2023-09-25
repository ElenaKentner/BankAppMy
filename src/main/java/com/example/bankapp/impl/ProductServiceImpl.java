package com.example.bankapp.impl;

import com.example.bankapp.entity.Product;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product updateProductById(String id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setStatus(updatedProduct.getStatus());
            existingProduct.setCurrencyCode(updatedProduct.getCurrencyCode());
            existingProduct.setInterestRate(updatedProduct.getInterestRate());
            existingProduct.setLimit(updatedProduct.getLimit());

            return productRepository.save(existingProduct);
        } else {
            throw new EntityNotFoundException(id + " not found");
        }
    }
}


