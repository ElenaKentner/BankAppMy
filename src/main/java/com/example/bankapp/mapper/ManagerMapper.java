package com.example.bankapp.mapper;

import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.entity.Manager;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO mapToDto(Manager manager);

    @Mapping(target = "id", ignore = true)
    Manager toEntity(ManagerDTO managerDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "firstName", source = "firstName",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lastName", source = "lastName",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateManagerFromDTO(ManagerDTO managerDTO, @MappingTarget Manager manager);

}
