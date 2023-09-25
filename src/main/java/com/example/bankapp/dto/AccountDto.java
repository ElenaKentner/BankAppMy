package com.example.bankapp.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String name;
    private String type;
    private String status;
    private Double balance;
    private String currencyCode;
}
