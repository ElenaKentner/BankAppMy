package com.example.bankapp.controller;

import com.example.bankapp.dto.ProductDTO;
import com.example.bankapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateById(@PathVariable String id, @RequestBody ProductDTO updatedProduct) {
        ProductDTO updated = productService.updateProductById(id, updatedProduct);
        return ResponseEntity.ok(updated);
    }
}


