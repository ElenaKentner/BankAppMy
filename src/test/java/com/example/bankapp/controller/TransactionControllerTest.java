package com.example.bankapp.controller;

import com.example.bankapp.dto.TransactionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/database/droptables.sql")
@Sql("/database/createtable.sql")
@Sql("/database/insert.sql")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void deleteTransaction() throws Exception {
        String transactionId = "18fbdb44-5563-47c0-b42e-20d5405e187d";
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/transactions/" + transactionId))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        TransactionDTO transactionDTOResult = objectMapper.readValue(result, TransactionDTO.class);

        Assertions.assertEquals(transactionId, transactionDTOResult.getId());

        mockMvc.perform(MockMvcRequestBuilders.delete("/transactions/" + transactionId))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/" + transactionId))
                .andExpect(status().is(204));
    }
}