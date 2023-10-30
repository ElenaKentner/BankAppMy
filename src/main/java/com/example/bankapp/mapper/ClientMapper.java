package com.example.bankapp.mapper;

import com.example.bankapp.dto.ClientDTO;
import com.example.bankapp.entity.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO mapToDto(Client client);

    @Mapping(target = "id", ignore = true)
    Client toEntity(ClientDTO clientDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "taxCode", target = "taxCode", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "status", target = "status", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "firstName", target = "firstName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "lastName", target = "lastName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "email", target = "email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "address", target = "address", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "phone", target = "phone", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientFromDTO(ClientDTO clientDTO, @MappingTarget Client client);
}
