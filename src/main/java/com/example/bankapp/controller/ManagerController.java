package com.example.bankapp.controller;

import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        managerService.deleteManagerById(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDTO> findManager(@PathVariable String id) {
        ManagerDTO managerDTO = managerService.findManagerById(id);
        return ResponseEntity.ok(managerDTO);
    }


}

