package com.example.bankapp.controller;

import com.example.bankapp.entity.Product;
import com.example.bankapp.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Test
    public void testUpdateProduct_Success() throws Exception {
        String productId = "valid-uuid-here";
        Product updatedProduct = new Product();

        Mockito.when(productService.updateProductById(productId, updatedProduct))
                .thenReturn(updatedProduct);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateProduct_InvalidUUID() throws Exception {
        String invalidUUID = "invalid-uuid";
        Product updatedProduct = new Product();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", invalidUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateProduct_NullProduct() throws Exception {
        String validUUID = "valid-uuid-here";

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", validUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateProduct_ProductNotFound() throws Exception {
        String validUUID = "valid-uuid-here";
        Product updatedProduct = new Product();

        Mockito.when(productService.updateProductById(validUUID, updatedProduct))
                .thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", validUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateProduct_Success_InvalidUUID() throws Exception {
        String invalidUUID = "invalid-uuid";
        Product updatedProduct = new Product();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", invalidUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(status().isBadRequest());
    }


}

