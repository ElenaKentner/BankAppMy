package com.example.bankapp.mapper;

import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO mapToDto(Manager manager);

    @Mapping(target = "id", ignore = true)
    Manager toEntity(ManagerDTO managerDTO);

}
