package com.example.bankapp.service.impl;

import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Override
    public void deleteManagerById(UUID id) {
        managerRepository.deleteById(id);
    }
}

