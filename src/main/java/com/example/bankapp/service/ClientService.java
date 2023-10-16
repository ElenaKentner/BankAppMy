package com.example.bankapp.service;

import com.example.bankapp.dto.ClientDTO;
import com.example.bankapp.entity.Client;

public interface ClientService {
    Client getClientById(String id);

    ClientDTO getClientDtoById(String id);
}
