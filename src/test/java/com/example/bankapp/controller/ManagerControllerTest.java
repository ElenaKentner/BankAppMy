package com.example.bankapp.controller;

import com.example.bankapp.dto.ManagerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

    @Test
    void createManager() throws Exception {
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setId("1678babb-7f73-4eb7-8dd6-c1f9988b2606");
        managerDTO.setFirstName("Pol");
        managerDTO.setLastName("Popkow");
        managerDTO.setStatus("NEW");

        String managerDtoString = objectMapper.writeValueAsString(managerDTO);

        MvcResult managerDtoResult = mockMvc.perform(MockMvcRequestBuilders.post("/managers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(managerDtoString))
                .andReturn();

        String managerResultJson = managerDtoResult.getResponse().getContentAsString();
        ManagerDTO managerResult = objectMapper.readValue(managerResultJson, ManagerDTO.class);

        Assertions.assertEquals(201, managerDtoResult.getResponse().getStatus());
        Assertions.assertEquals(managerDTO.getStatus(), managerResult.getStatus());
        Assertions.assertEquals(managerDTO.getFirstName(), managerResult.getFirstName());
        Assertions.assertEquals(managerDTO.getLastName(), managerResult.getLastName());

    }
}