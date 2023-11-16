package com.example.bankapp.service;

import com.example.bankapp.dto.ClientDTO;
import com.example.bankapp.entity.Client;

public interface ClientService {

    Client getClientById(String id);

    ClientDTO getClientDtoById(String id);

    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO updateClient(String id, ClientDTO updatedClientDTO);

    void deleteClient(String id);

    Client findByEmailWithPassword(String email, String password);
}
