package com.example.bankapp.service;

import com.example.bankapp.entity.Product;

public interface ProductService {
    Product updateProductById(String id, Product updatedProduct);
}

