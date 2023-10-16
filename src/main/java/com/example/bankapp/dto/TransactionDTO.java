package com.example.bankapp.dto;

import lombok.*;
@Data
public class TransactionDTO {
    private String id;
    private String debitAccountId;
    private String creditAccountId;
    private String status;
    private String amount;
    private String description;
}
