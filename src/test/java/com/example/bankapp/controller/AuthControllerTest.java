package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientCredentialsDTO;
import com.example.bankapp.security.JwtService;
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
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtService jwtService;

    @Test
    void auth() throws Exception {
        ClientCredentialsDTO clientCredentialsDTO = new ClientCredentialsDTO();
        clientCredentialsDTO.setEmail("ivan@gmail.com");
        clientCredentialsDTO.setPassword("12qwaszx");

        String credentialDtoString = objectMapper.writeValueAsString(clientCredentialsDTO);

        String tokenResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(credentialDtoString))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String email = jwtService.getEmailFromToken(tokenResult);

        Assertions.assertEquals(clientCredentialsDTO.getEmail(), email);
    }
}