package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Test
    public void testCreateAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId(UUID.randomUUID());

        Account createdAccount = new Account();

        when(accountService.createAccount(any(AccountDTO.class))).thenReturn(createdAccount);

        ResponseEntity<AccountDTO> responseEntity = accountController.createAccount(accountDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        verify(accountService, times(1)).createAccount(any(AccountDTO.class));
    }

    @Test
    public void testCreateAccountWithNullDTO() {
        ResponseEntity<AccountDTO> responseEntity = accountController.createAccount(null);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void testCreateAccountServiceFailure() {
        when(accountService.createAccount(any(AccountDTO.class))).thenReturn(null);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId(UUID.randomUUID());

        ResponseEntity<AccountDTO> responseEntity = accountController.createAccount(accountDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        verify(accountService, times(1)).createAccount(any(AccountDTO.class));
        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void testCreateAccountWithMissingClientId() {
        AccountDTO accountDTO = new AccountDTO();

        ResponseEntity<AccountDTO> responseEntity = accountController.createAccount(accountDTO);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        verifyNoMoreInteractions(accountService);
    }

    @Test
    public void testCreateAccountWithExistingClient() {
        when(accountService.createAccount(any(AccountDTO.class))).thenThrow(EntityExistsException.class);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId(UUID.randomUUID());

        ResponseEntity<AccountDTO> responseEntity = accountController.createAccount(accountDTO);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

        verify(accountService, times(1)).createAccount(any(AccountDTO.class));
        verifyNoMoreInteractions(accountService);
    }

}

