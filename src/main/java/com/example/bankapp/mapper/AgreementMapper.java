package com.example.bankapp.mapper;

import com.example.bankapp.dto.AgreementDTO;
import com.example.bankapp.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "account.id", target = "accountId")
    AgreementDTO mapToDto(Agreement agreement);
}
