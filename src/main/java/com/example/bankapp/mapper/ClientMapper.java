package com.example.bankapp.mapper;

import com.example.bankapp.dto.ClientDTO;
import com.example.bankapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO mapToDto(Client client);

    @Mapping(target = "id", ignore = true)
    Client toEntity(ClientDTO clientDTO);
}
