package com.example.bankapp.impl;

import com.example.bankapp.entity.Client;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client getClientById(String id) {
        return clientRepository.findById(id).orElse(null);
    }
}






