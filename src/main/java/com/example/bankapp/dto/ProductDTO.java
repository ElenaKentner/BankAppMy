package com.example.bankapp.dto;

import com.example.bankapp.entity.enums.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String name;
    private String status;
    private String interestRate;;
    private String minLimit;
}
