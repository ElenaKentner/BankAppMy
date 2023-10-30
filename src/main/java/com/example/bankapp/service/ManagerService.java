package com.example.bankapp.service;

import com.example.bankapp.dto.ManagerDTO;

import java.util.UUID;

public interface ManagerService {
    void deleteManagerById(UUID id);

    ManagerDTO findManagerById(String id);

}

