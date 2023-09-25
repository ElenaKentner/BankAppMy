package com.example.bankapp.controller;

import com.example.bankapp.entity.Product;
import com.example.bankapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PutMapping("/{id}")
    public Product updateById(@PathVariable String id, @RequestBody Product updatedProduct) {
        return productService.updateProductById(id, updatedProduct);
    }
}
