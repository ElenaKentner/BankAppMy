package com.example.bankapp.controller;

import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.bankapp.util.UUIDValidator.isValidUUID;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteById(@RequestParam String id) {
        if (!isValidUUID(id)) {
            return ResponseEntity.badRequest().build();
        }

        managerService.deleteManagerById(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}

