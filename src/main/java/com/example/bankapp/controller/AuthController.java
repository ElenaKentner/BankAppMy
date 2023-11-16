package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientCredentialsDTO;
import com.example.bankapp.entity.Client;
import com.example.bankapp.security.JwtService;
import com.example.bankapp.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final ClientService clientService;
    private final JwtService jwtService;

    public AuthController(ClientService clientService, JwtService jwtService) {
        this.clientService = clientService;
        this.jwtService = jwtService;
    }

    @PostMapping()
    public ResponseEntity<String> auth(@RequestBody ClientCredentialsDTO clientCredentialsDTO) {
        Client client = clientService.findByEmailWithPassword(clientCredentialsDTO.getEmail(),
                clientCredentialsDTO.getPassword());
        return ResponseEntity.ok(jwtService.generateToken(client.getEmail()));
    }
}
