package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account mapToEntity(AccountDTO accountDTO);

    @Mapping(source = "agreement.product.name", target = "productName")
    @Mapping(source = "agreement.product.interestRate", target = "interestRate")
    @Mapping(source = "client", target = "ownerFullName", qualifiedByName = "getFullName")
    AccountDTO mapToDto(Account account);

    @Mapping(target = "id", ignore = true)
    Account updateAccountFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

    List<AccountDTO> mapToDtoList(List<Account> accountList);

    @Named("getFullName")
    default String getFullName(Client client) {
        return client.getFirstName() + " " + client.getLastName();
    }
}
