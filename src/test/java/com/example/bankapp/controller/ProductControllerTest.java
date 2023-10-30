package com.example.bankapp.controller;

import com.example.bankapp.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/database/droptables.sql")
@Sql("/database/createtable.sql")
@Sql("/database/insert.sql")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void updateById() throws Exception {
        String productId = "afc9da7c-299d-4dd1-93bc-d71de8e5d1a6";
        ProductDTO productDTO = new ProductDTO();
        productDTO.setStatus("BLOCKED");
        productDTO.setInterestRate("2.50");

        String productDtoString = objectMapper.writeValueAsString(productDTO);

        String productDtoResultJson = mockMvc.perform(MockMvcRequestBuilders.put("/products/update/"
                                + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productDtoString))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductDTO updatedProductDto = objectMapper.readValue(productDtoResultJson, ProductDTO.class);

        Assertions.assertEquals(productDTO.getStatus(), updatedProductDto.getStatus());
        Assertions.assertEquals(productDTO.getInterestRate(), updatedProductDto.getInterestRate());

        Assertions.assertNotNull(updatedProductDto.getName());
        Assertions.assertNotNull(updatedProductDto.getMinLimit());

    }
}