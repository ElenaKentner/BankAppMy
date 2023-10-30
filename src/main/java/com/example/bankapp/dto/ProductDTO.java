package com.example.bankapp.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String status;
    private String interestRate;
    private String minLimit;
}
