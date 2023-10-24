package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.dto.ErrorDto;
import com.example.bankapp.entity.Account;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/database/droptables.sql")
@Sql("/database/createtable.sql")
@Sql("/database/insert.sql")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId("c48a263c-5a20-413e-8c9c-d89d83b1ee41");
        accountDTO.setType("CHECKING");
        accountDTO.setCurrencyCode("EUR");

        String accountDtoString = objectMapper.writeValueAsString(accountDTO);

        MvcResult accountDtoResult = mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountDtoString))
                .andReturn();

        String accountResultJson = accountDtoResult.getResponse().getContentAsString();
        Account accountResult = objectMapper.readValue(accountResultJson, Account.class);

        Assertions.assertEquals(201, accountDtoResult.getResponse().getStatus());
        Assertions.assertEquals(accountDTO.getType(), accountResult.getType().toString());
        Assertions.assertEquals(accountDTO.getCurrencyCode(), accountResult.getCurrencyCode().toString());

    }

    @Test
    void deleteAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId("c48a263c-5a20-413e-8c9c-d89d83b1ee41");
        accountDTO.setType("CHECKING");
        accountDTO.setCurrencyCode("EUR");

        String accountDtoString = objectMapper.writeValueAsString(accountDTO);

        MvcResult accountDtoResult = mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountDtoString))
                .andReturn();

        Assertions.assertEquals(201, accountDtoResult.getResponse().getStatus());

        String accountResultJson = accountDtoResult.getResponse().getContentAsString();
        Account accountResult = objectMapper.readValue(accountResultJson, Account.class);

        UUID createdAccountId = accountResult.getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/" + createdAccountId))
                .andExpect(status().isOk());

        MvcResult accountDtoGetResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/accounts/" + createdAccountId))
                        .andReturn();

        Assertions.assertEquals(204, accountDtoGetResult.getResponse().getStatus());
        String resultErrorJson = accountDtoGetResult.getResponse().getContentAsString();
        ErrorDto errorDtoResult = objectMapper.readValue(resultErrorJson, ErrorDto.class);
        Assertions.assertEquals("Account not found", errorDtoResult.getMessage());
    }

    @Test
    void findAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId("c48a263c-5a20-413e-8c9c-d89d83b1ee41");
        accountDTO.setType("CHECKING");
        accountDTO.setCurrencyCode("EUR");

        String accountDtoString = objectMapper.writeValueAsString(accountDTO);

        MvcResult accountDtoResult = mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountDtoString))
                .andReturn();

        Assertions.assertEquals(201, accountDtoResult.getResponse().getStatus());

        String accountResultJson = accountDtoResult.getResponse().getContentAsString();
        Account accountResult = objectMapper.readValue(accountResultJson, Account.class);
        UUID createdAccountId = accountResult.getId();

        MvcResult findAccountResult = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/" + createdAccountId))
                .andReturn();

        Assertions.assertEquals(200, findAccountResult.getResponse().getStatus());

    }

    @Test
    void updateAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId("c48a263c-5a20-413e-8c9c-d89d83b1ee41");
        accountDTO.setType("CHECKING");
        accountDTO.setCurrencyCode("EUR");

        String accountDtoString = objectMapper.writeValueAsString(accountDTO);

        MvcResult accountDtoResult = mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountDtoString))
                .andReturn();

        Assertions.assertEquals(201, accountDtoResult.getResponse().getStatus());

        String accountResultJson = accountDtoResult.getResponse().getContentAsString();
        Account accountResult = objectMapper.readValue(accountResultJson, Account.class);
        UUID createdAccountId = accountResult.getId();

        AccountDTO updatedAccountDTO = new AccountDTO();
        updatedAccountDTO.setType("SAVINGS");
        updatedAccountDTO.setCurrencyCode("USD");

        String updatedAccountDtoString = objectMapper.writeValueAsString(updatedAccountDTO);

        MvcResult updateAccountResult = mockMvc.perform(MockMvcRequestBuilders.put("/accounts/update/" + createdAccountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedAccountDtoString))
                .andReturn();

        Assertions.assertEquals(200, updateAccountResult.getResponse().getStatus());
    }

    @Test
    void getByProductName() throws Exception {
        String clientIdA = UUID.randomUUID().toString();
        String clientIdB = UUID.randomUUID().toString();
        String clientIdC = UUID.randomUUID().toString();

        AccountDTO accountA = new AccountDTO();
        accountA.setClientId(clientIdA);
        accountA.setType("CHECKING");
        accountA.setCurrencyCode("EUR");
        accountA.setProductName("ProductA");

        AccountDTO accountB = new AccountDTO();
        accountB.setClientId(clientIdB);
        accountB.setType("SAVINGS");
        accountB.setCurrencyCode("PLN");
        accountB.setProductName("ProductB");

        AccountDTO accountC = new AccountDTO();
        accountC.setClientId(clientIdC);
        accountC.setType("CHECKING");
        accountC.setCurrencyCode("USD");
        accountC.setProductName("ProductC");

        String accountAJson = objectMapper.writeValueAsString(accountA);
        String accountBJson = objectMapper.writeValueAsString(accountB);
        String accountCJson = objectMapper.writeValueAsString(accountC);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountAJson))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountBJson))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountCJson))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/by-product-name")
                        .param("productName", "ProductB"))
                .andReturn();

        String resultJson = result.getResponse().getContentAsString();
        List<AccountDTO> accountDTOList = objectMapper.readValue(resultJson, new TypeReference<>() {
        });

        boolean containsProductB = accountDTOList.stream()
                .anyMatch(accountDTO -> "ProductB".equals(accountDTO.getProductName()));
        Assertions.assertTrue(containsProductB);
    }

}