package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientDTO;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable String id) {
        ClientDTO client = clientService.getClientDtoById(id);
        return ResponseEntity.ok(client);
    }
    @PostMapping("/create")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO createdClient = clientService.createClient(clientDTO);
        return ResponseEntity.created(URI.create("/" + createdClient.getId())).body(createdClient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable String id, @RequestBody ClientDTO updatedClientDTO) {
        ClientDTO updatedClient = clientService.updateClient(id, updatedClientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client deleted successfully");
    }
}
