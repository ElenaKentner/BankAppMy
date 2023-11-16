package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.dto.TransactionDTO;
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
    @WithUserDetails("ivan@gmail.com")
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

    @Test
    @WithUserDetails("ivan@gmail.com")
    void createTransaction() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setDebitAccountName("988776544332");
        transactionDTO.setCreditAccountName("657483958765");
        transactionDTO.setAmount("100");
        transactionDTO.setDescription("qwewaaszd");

        String debitAccountId = "e0a35315-4bb8-485a-a108-93b346ab452e";
        String creditAccountId = "11f7986c-c1d8-4231-838a-e84b17ccebdb";
        String debitAccountJsonBeforeTransaction = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"
                        + debitAccountId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String creditAccountJsonBeforeTransaction = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"
                        + creditAccountId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        AccountDTO debitAccountDTOBefore = objectMapper.readValue(debitAccountJsonBeforeTransaction, AccountDTO.class);
        AccountDTO creditAccountDTOBefore = objectMapper.readValue(creditAccountJsonBeforeTransaction, AccountDTO.class);

        String transactionDtoString = objectMapper.writeValueAsString(transactionDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionDtoString))
                .andExpect(status().isCreated())
                .andReturn();

        String debitAccountJsonAfterTransaction = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"
                        + debitAccountId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String creditAccountJsonAfterTransaction = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"
                        + creditAccountId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        AccountDTO debitAccountDTOAfter = objectMapper.readValue(debitAccountJsonAfterTransaction, AccountDTO.class);
        AccountDTO creditAccountDTOAfter = objectMapper.readValue(creditAccountJsonAfterTransaction, AccountDTO.class);

        double debitBalanceBefore = Double.parseDouble(debitAccountDTOBefore.getBalance());
        double creditBalanceBefore = Double.parseDouble(creditAccountDTOBefore.getBalance());
        double debitBalanceAfter = Double.parseDouble(debitAccountDTOAfter.getBalance());
        double creditBalanceAfter = Double.parseDouble(creditAccountDTOAfter.getBalance());
        double amount = Double.parseDouble(transactionDTO.getAmount());

        Assertions.assertEquals(debitBalanceBefore - amount, debitBalanceAfter);
        Assertions.assertEquals(creditBalanceBefore + amount, creditBalanceAfter);
    }
}