package com.example.bankapp.mapper;

import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO mapToDto(Manager manager);

}
