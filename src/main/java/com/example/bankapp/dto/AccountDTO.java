package com.example.bankapp.dto;

import lombok.*;

@Data
public class AccountDTO {
    private String id;
    private String clientId;
    private String currencyCode;
    private String type;
    private String balance;
    private String name;
    private String productName;
    private String interestRate;
    private String ownerFullName;
}
