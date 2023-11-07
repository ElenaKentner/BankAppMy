package com.example.bankapp.controller;

import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping("/create")
    public ResponseEntity<ManagerDTO> createManager(@RequestBody ManagerDTO managerDTO) {
        ManagerDTO createdManager = managerService.createManager(managerDTO);
        return ResponseEntity.created(URI.create("/" + createdManager.getId())).body(createdManager);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ManagerDTO> updateManager(@PathVariable String id, @RequestBody ManagerDTO updatemanagerDTO) {
        ManagerDTO updateManager = managerService.updateManager(id, updatemanagerDTO);
        return ResponseEntity.ok(updateManager);
    }


}

