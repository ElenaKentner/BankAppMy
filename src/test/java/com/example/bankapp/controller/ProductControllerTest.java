package com.example.bankapp.controller;

import com.example.bankapp.dto.ProductDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

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
    @WithUserDetails("ivan@gmail.com")
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

    @Test
    @WithUserDetails("ivan@gmail.com")
    void getAllActiveProductsTest() throws Exception {
        List<ProductDTO> expected = getAllProductDTOS();

        String productDtoResultJson = mockMvc.perform(MockMvcRequestBuilders.get("/products/all"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<ProductDTO> actual = objectMapper.readValue(productDtoResultJson, new TypeReference<>() {
        });
        Assertions.assertEquals(expected, actual);

    }

    private static List<ProductDTO> getAllProductDTOS() {
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setStatus("ACTIVE");
        productDTO1.setInterestRate("1.5000");
        productDTO1.setId("afc9da7c-299d-4dd1-93bc-d71de8e5d1a6");
        productDTO1.setName("name1");
        productDTO1.setMinLimit("100");


        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setStatus("ACTIVE");
        productDTO2.setInterestRate("0.5000");
        productDTO2.setId("4222e119-4a2b-4a6a-ba25-c263aa3b30a5");
        productDTO2.setName("name2");
        productDTO2.setMinLimit("200");

        return List.of(productDTO1, productDTO2);
    }
}