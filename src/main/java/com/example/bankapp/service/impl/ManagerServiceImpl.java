package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.entity.Manager;
import com.example.bankapp.mapper.ManagerMapper;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Override
    @Transactional
    public void deleteManagerById(UUID id) {
        managerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ManagerDTO findManagerById(String id) {
        Manager manager = managerRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new EntityNotFoundException("Manager not found"));
        return managerMapper.mapToDto(manager);
    }

    @Override
    @Transactional
    public ManagerDTO createManager(ManagerDTO managerDTO) {
        Manager manager = managerMapper.toEntity(managerDTO);
        manager.setCreatedAt(LocalDateTime.now());
        manager.setUpdatedAt(LocalDateTime.now());
        managerRepository.save(manager);

        return managerMapper.mapToDto(manager);
    }

    @Transactional
    @Override
    public ManagerDTO updateManager(String id, ManagerDTO updatedManagerDTO) {
        Manager manager = new Manager();

        return managerMapper.mapToDto(manager);
    }

}

