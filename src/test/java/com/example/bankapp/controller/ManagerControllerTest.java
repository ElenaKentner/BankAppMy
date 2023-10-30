package com.example.bankapp.controller;

import com.example.bankapp.dto.ManagerDTO;
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
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void deleteById() throws Exception {
        String mangerId = "1370ec78-5c28-46c3-b8dd-8ebee695daea";
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/managers/" + mangerId))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        ManagerDTO managerDTOResult = objectMapper.readValue(result, ManagerDTO.class);

        Assertions.assertEquals(mangerId, managerDTOResult.getId());

        mockMvc.perform(MockMvcRequestBuilders.delete("/managers/" + mangerId))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/managers/" + mangerId))
                .andExpect(status().is(204));
    }
}