package com.example.bankapp.service;

import com.example.bankapp.dto.ProductDTO;
import com.example.bankapp.entity.Product;

public interface ProductService {
    Product updateProductById(String id, ProductDTO updatedProduct);
}

