package com.example.bankapp.controller;

import com.example.bankapp.dto.AgreementDTO;
import com.example.bankapp.entity.enums.Status;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/database/droptables.sql")
@Sql("/database/createtable.sql")
@Sql("/database/insert.sql")
class AgreementControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception {
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setAccountId("11f7986c-c1d8-4231-838a-e84b17ccebdb");
        agreementDTO.setProductId("afc9da7c-299d-4dd1-93bc-d71de8e5d1a6");

        String agreementDtoString = objectMapper.writeValueAsString(agreementDTO);

        String agreementResultJson = mockMvc.perform(MockMvcRequestBuilders.post("/agreements/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agreementDtoString))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        AgreementDTO agreementDtoResult = objectMapper.readValue(agreementResultJson, AgreementDTO.class);

        Assertions.assertNotNull(agreementDtoResult.getId());
        Assertions.assertEquals(Status.NEW.toString(), agreementDtoResult.getStatus());
        Assertions.assertEquals(agreementDTO.getAccountId(), agreementDtoResult.getAccountId());
        Assertions.assertEquals(agreementDTO.getProductId(), agreementDtoResult.getProductId());

    }
}