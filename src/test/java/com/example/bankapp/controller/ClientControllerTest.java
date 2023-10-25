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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


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
//        ClientDTO clientDTO = getClientDto();
//        clientDTO.setId(null);
//
//        String clientDTOString = objectMapper.writeValueAsString(clientDTO);
//
//        MvcResult clientDtoMvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/clients/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(clientDTOString))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        String clientResultDtoJson = clientDtoMvcResult.getResponse().getContentAsString();
//        ClientDTO clientDtoResult = objectMapper.readValue(clientResultDtoJson, ClientDTO.class);
//
//        Assertions.assertNotNull(clientDtoResult.getId());
//
//        clientDtoResult.setId(null);
//
//        Assertions.assertEquals(clientDTO, clientDtoResult);
        ClientDTO clientDTO = getClientDto();

        mockMvc.perform(MockMvcRequestBuilders.post("/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
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

    private ClientDTO getClientDto() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId("fee1a328-e29b-4541-9284-b7c679e8a58e");
        clientDTO.setFirstName("Vasja");
        clientDTO.setLastName("Petrov");
        clientDTO.setEmail("petrov@gmail.com");
        clientDTO.setStatus("NEW");
        clientDTO.setAddress("Warshaw, Kalina, 11");
        clientDTO.setTaxCode("98765432");
        clientDTO.setPhone("6789908643");

        return clientDTO;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
