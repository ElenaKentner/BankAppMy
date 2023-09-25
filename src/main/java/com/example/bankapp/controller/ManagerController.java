package com.example.bankapp.controller;

import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @DeleteMapping("/deleteByName")
    public void deleteByName(@RequestParam String name) {
        managerService.deleteManagerByName(name);
    }
}

