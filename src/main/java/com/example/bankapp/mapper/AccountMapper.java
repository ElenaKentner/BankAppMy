package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account mapToEntity(AccountDTO accountDTO);

    @Mapping(source = "agreement.product.name", target = "productName")
    @Mapping(source = "agreement.product.interestRate", target = "interestRate")
    @Mapping(source = "client", target = "ownerFullName", qualifiedByName = "getFullName")
    @Mapping(source = "client.id", target = "clientId")
    AccountDTO mapToDto(Account account);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", source = "balance", qualifiedByName = "stringToBigDecimal",
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateAccountFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

    List<AccountDTO> mapToDtoList(List<Account> accountList);

    @Named("getFullName")
    default String getFullName(Client client) {
        return client.getFirstName() + " " + client.getLastName();
    }

    @Named("stringToBigDecimal")
    default BigDecimal stringToBigDecimal(String value) {
        return new BigDecimal(value);
    }
}
