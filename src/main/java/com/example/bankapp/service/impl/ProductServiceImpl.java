package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ProductDTO;
import com.example.bankapp.entity.Product;
import com.example.bankapp.mapper.ProductMapper;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductDTO updateProductById(String id, ProductDTO updatedProduct) {
        Product existingProduct = productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException(id + " not found"));

        productMapper.updateProduct(updatedProduct, existingProduct);

        existingProduct.setUpdatedAt(LocalDateTime.now());

        productRepository.save(existingProduct);

        return productMapper.mapToDto(existingProduct);
    }

    @Transactional
    @Override
    public List<ProductDTO> getAllActive() {
        List<Product> productList = productRepository.findAllActive();
        return productMapper.mapToListDto(productList);
    }
}



