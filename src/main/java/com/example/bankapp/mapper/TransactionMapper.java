package com.example.bankapp.mapper;

import com.example.bankapp.dto.TransactionDTO;
import com.example.bankapp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "creditAccount.id", target = "creditAccountId")
    @Mapping(source = "debitAccount.id", target = "debitAccountId")
    TransactionDTO mapToDto(Transaction transaction);

}
