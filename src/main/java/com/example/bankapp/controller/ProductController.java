package com.example.bankapp.controller;

import com.example.bankapp.entity.Product;
import com.example.bankapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.bankapp.util.UUIDValidator.isValidUUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateById(@PathVariable String id, @RequestBody Product updatedProduct) {
        if (!isValidUUID(id) || updatedProduct == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Product updated = productService.updateProductById(id, updatedProduct);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
