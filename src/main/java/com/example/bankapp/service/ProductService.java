package com.example.bankapp.service;

import com.example.bankapp.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO updateProductById(String id, ProductDTO updatedProduct);

    List<ProductDTO> getAllActive();
}

