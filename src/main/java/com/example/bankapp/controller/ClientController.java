package com.example.bankapp.controller;

import com.example.bankapp.entity.Client;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{id}")
    public Client getById(@PathVariable String id) {
        return clientService.getClientById(id);
    }
}
