package com.example.bankapp.controller;

import com.example.bankapp.entity.Client;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.bankapp.util.UUIDValidator.isValidUUID;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {

        if (!isValidUUID(id)) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }

        Client client = clientService.getClientById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);
    }
}
