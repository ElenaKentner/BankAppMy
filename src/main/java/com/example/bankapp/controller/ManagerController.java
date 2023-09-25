package com.example.bankapp.controller;

import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam UUID id) {
        managerService.deleteManagerById(id);
    }
}

