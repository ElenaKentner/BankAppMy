package com.example.bankapp.controller;

import com.example.bankapp.entity.Product;
import com.example.bankapp.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void testUpdateProduct_Success() throws Exception {
        String productId = "valid-uuid-here";
        Product updatedProduct = new Product();

        when(productService.updateProductById(productId, updatedProduct))
                .thenReturn(updatedProduct);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateProduct_InvalidUUID() throws Exception {
        String invalidUUID = "invalid-uuid";
        Product updatedProduct = new Product();

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", invalidUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdateProduct_NullProduct() throws Exception {
        String validUUID = "valid-uuid-here";

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", validUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdateProduct_ProductNotFound() throws Exception {
        String validUUID = "valid-uuid-here";
        Product updatedProduct = new Product();

        when(productService.updateProductById(validUUID, updatedProduct))
                .thenThrow(new EntityNotFoundException());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", validUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateProduct_Success_InvalidUUID() throws Exception {
        String invalidUUID = "invalid-uuid";
        Product updatedProduct = new Product();

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", invalidUUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


