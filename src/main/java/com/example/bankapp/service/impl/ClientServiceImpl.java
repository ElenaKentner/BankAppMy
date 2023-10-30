package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ClientDTO;
import com.example.bankapp.entity.Client;
import com.example.bankapp.mapper.ClientMapper;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Transactional
    @Override
    public Client getClientById(String id) {
        return clientRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException(id + " not found"));
    }

    @Transactional
    @Override
    public ClientDTO getClientDtoById(String id) {
        Client client = getClientById(id);

        return clientMapper.mapToDto(client);
    }

    @Transactional
    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(LocalDateTime.now());
        clientRepository.save(client);

        return clientMapper.mapToDto(client);
    }

    @Transactional
    @Override
    public ClientDTO updateClient(String id, ClientDTO updatedClientDTO) {
        Client client = getClientById(id);
        clientMapper.updateClientFromDTO(updatedClientDTO, client);
//        if (updatedClientDTO.getStatus() != null) {
//            client.setStatus(Status.valueOf(updatedClientDTO.getStatus()));
//        }
//        if (updatedClientDTO.getTaxCode() != null) {
//            client.setTaxCode(updatedClientDTO.getTaxCode());
//        }
//        if (updatedClientDTO.getFirstName() != null) {
//            client.setFirstName(updatedClientDTO.getFirstName());
//        }
//        if (updatedClientDTO.getLastName() != null) {
//            client.setLastName(updatedClientDTO.getLastName());
//        }
//        if (updatedClientDTO.getEmail() != null) {
//            client.setEmail(updatedClientDTO.getEmail());
//        }
//        if (updatedClientDTO.getAddress() != null) {
//            client.setAddress(updatedClientDTO.getAddress());
//        }
//        if (updatedClientDTO.getPhone() != null) {
//            client.setPhone(updatedClientDTO.getPhone());
//        }

        clientRepository.save(client);

        return clientMapper.mapToDto(client);
    }

    @Transactional
    @Override
    public void deleteClient(String id) {
        clientRepository.deleteById(UUID.fromString(id));

    }
}






