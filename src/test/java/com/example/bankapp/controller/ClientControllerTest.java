package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Sql("/database/droptables.sql")
@Sql("/database/createtable.sql")
@Sql("/database/insert.sql")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createClient() throws Exception {

    }


    @Test
    void getById() throws Exception {

    }


    @Test
    void updateClient() throws Exception {

    }

    @Test
    void deleteClient() throws Exception {

    }

    private ClientDTO createClient(UUID clientId, String firstName, String lastName, String email) throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientId.toString());
        clientDTO.setFirstName(firstName);
        clientDTO.setLastName(lastName);
        clientDTO.setEmail(email);

        String clientDtoString = objectMapper.writeValueAsString(clientDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDtoString))
                .andExpect(status().isOk())
                .andReturn();

        String createdClientJson = result.getResponse().getContentAsString();
        return objectMapper.readValue(createdClientJson, ClientDTO.class);
    }

}
