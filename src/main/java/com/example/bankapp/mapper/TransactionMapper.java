package com.example.bankapp.mapper;

import com.example.bankapp.dto.TransactionDTO;
import com.example.bankapp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface TransactionMapper {

    @Mapping(source = "creditAccount.name", target = "creditAccountName")
    @Mapping(source = "debitAccount.name", target = "debitAccountName")
    TransactionDTO mapToDto(Transaction transaction);

    @Mapping(source = "amount", target = "amount", qualifiedByName = "stringToBigDecimal")
    Transaction mapToEntity(TransactionDTO transactionDTO);
}
