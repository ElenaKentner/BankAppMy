package com.example.bankapp.service;

import com.example.bankapp.dto.ProductDTO;

public interface ProductService {
    ProductDTO updateProductById(String id, ProductDTO updatedProduct);
}

