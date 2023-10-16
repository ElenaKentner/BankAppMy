package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account mapToEntity(AccountDTO accountDTO);
    AccountDTO mapToDto(Account account);

    @Mapping(target = "id", ignore = true)
    Account updateAccountFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

}
