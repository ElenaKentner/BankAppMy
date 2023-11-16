package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientDTO;
import com.example.bankapp.dto.ErrorDTO;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    @WithUserDetails("ivan@gmail.com")
    void createClientPositiveTest() throws Exception {
        ClientDTO clientDTO = getClientDto();
        clientDTO.setId(null);

        String clientDTOString = objectMapper.writeValueAsString(clientDTO);

        MvcResult clientDtoMvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDTOString))
                .andExpect(status().isCreated())
                .andReturn();

        String clientResultDtoJson = clientDtoMvcResult.getResponse().getContentAsString();
        ClientDTO clientDtoResult = objectMapper.readValue(clientResultDtoJson, ClientDTO.class);

        Assertions.assertNotNull(clientDtoResult.getId());

        clientDtoResult.setId(null);

        Assertions.assertEquals(clientDTO, clientDtoResult);

    }

    @Test
    @WithUserDetails("ivan@gmail.com")
    void getByIdTest() throws Exception {
        ClientDTO expected = getClientDto();

        MvcResult clientDtoMvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/clients/" +
                                expected.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String clientResultDtoJson = clientDtoMvcResult.getResponse().getContentAsString();
        ClientDTO clientDtoResult = objectMapper.readValue(clientResultDtoJson, ClientDTO.class);

        Assertions.assertEquals(expected, clientDtoResult);
    }


    @Test
    @WithUserDetails("ivan@gmail.com")
    void updateClientTest() throws Exception {
        ClientDTO expected = getClientDto();
        ClientDTO clientDtoToUpdate = new ClientDTO();
        clientDtoToUpdate.setStatus("ACTIVE");
        expected.setStatus("ACTIVE");

        String clientDTOString = objectMapper.writeValueAsString(clientDtoToUpdate);

        MvcResult clientDtoMvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/clients/update/" +
                                expected.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDTOString))
                .andExpect(status().isOk())
                .andReturn();

        String clientResultDtoJson = clientDtoMvcResult.getResponse().getContentAsString();
        ClientDTO clientDtoResult = objectMapper.readValue(clientResultDtoJson, ClientDTO.class);

        Assertions.assertEquals(expected, clientDtoResult);

    }

    @Test
    @WithUserDetails("ivan@gmail.com")
    void deleteClientTest() throws Exception {
        ClientDTO clientDTO = getClientDto();

        MvcResult clientDtoMvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/clients/" +
                        clientDTO.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String clientResultDtoJson = clientDtoMvcResult.getResponse().getContentAsString();
        ClientDTO clientDtoResult = objectMapper.readValue(clientResultDtoJson, ClientDTO.class);

        Assertions.assertEquals(clientDTO, clientDtoResult);

         mockMvc.perform(MockMvcRequestBuilders.delete("/clients/" +
                        clientDTO.getId()))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.get("/clients/" +
                        clientDTO.getId()))
                .andExpect(status().is(204))
                .andReturn();
    }

    @Test
    @WithUserDetails("ivan@gmail.com")
    void createClientNegativeTest() throws Exception {
        ClientDTO clientDTO = getClientDto();
        clientDTO.setId(null);
        clientDTO.setFirstName("Vasjaж");

        String clientDTOString = objectMapper.writeValueAsString(clientDTO);

        String errorDtoResult = mockMvc.perform(MockMvcRequestBuilders.post("/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDTOString))
                .andExpect(status().is(400))
                .andReturn()
                .getResponse().getContentAsString();

        ErrorDTO result = objectMapper.readValue(errorDtoResult, ErrorDTO.class);

        Assertions.assertEquals("first name must contain only latin alphabet", result.getMessage());

    }

    @Test
    @WithUserDetails("ivan@gmail.com")
    void createClientNegativeInternalServerErrorTest() throws Exception {
        ClientDTO clientDTO = getClientDto();
        clientDTO.setId(null);
        clientDTO.setStatus("Error");

        String clientDTOString = objectMapper.writeValueAsString(clientDTO);

        String errorDtoResult = mockMvc.perform(MockMvcRequestBuilders.post("/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDTOString))
                .andExpect(status().is(500))
                .andReturn()
                .getResponse().getContentAsString();

        ErrorDTO result = objectMapper.readValue(errorDtoResult, ErrorDTO.class);

        Assertions.assertEquals("No enum constant com.example.bankapp.entity.enums.Status.Error",
                result.getMessage());

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

}
