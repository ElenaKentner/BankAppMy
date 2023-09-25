package com.example.bankapp.impl;

import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Override
    public void deleteManagerByName(String name) {
        managerRepository.deleteByName(name);
    }
}

