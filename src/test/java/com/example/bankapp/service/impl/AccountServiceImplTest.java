package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.entity.enums.CurrencyCode;
import com.example.bankapp.entity.enums.Status;
import com.example.bankapp.entity.enums.Type;
import com.example.bankapp.mapper.AccountMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.service.ClientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithUserDetails;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    private Account account;

    @Mock
    private AccountMapper accountMapper;
    @Mock
    private ClientService clientService;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setType(Type.CHECKING);
        account.setCurrencyCode(CurrencyCode.EUR);
    }

    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    @WithUserDetails("ivan@gmail.com")
    void createAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId("c48a263c-5a20-413e-8c9c-d89d83b1ee41");
        accountDTO.setType("CHECKING");
        accountDTO.setCurrencyCode("EUR");

        Client client = new Client();
        client.setId(UUID.fromString("c48a263c-5a20-413e-8c9c-d89d83b1ee41"));

        Account expected = new Account();
        expected.setClient(client);
        expected.setBalance(BigDecimal.ZERO);
        expected.setStatus(Status.NEW);

        when(accountMapper.mapToEntity(accountDTO)).thenReturn(account);

        when(clientService.getClientById(accountDTO.getClientId())).thenReturn(client);

        when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArguments()[0]);

        Account actual = accountService.createAccount(accountDTO);

        Assertions.assertEquals(expected.getBalance(), actual.getBalance());
        Assertions.assertEquals(expected.getClient(), actual.getClient());
        Assertions.assertEquals(expected.getStatus(), actual.getStatus());
        Assertions.assertNotNull(actual.getName());
        Assertions.assertNotNull(actual.getCreatedAt());
        Assertions.assertNotNull(actual.getUpdatedAt());
    }
}