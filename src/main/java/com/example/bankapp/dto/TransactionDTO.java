package com.example.bankapp.dto;

import lombok.*;
@Data
public class TransactionDTO {
    private String id;
    private String debitAccountName;
    private String creditAccountName;
    private String status;
    private String amount;
    private String description;
}
