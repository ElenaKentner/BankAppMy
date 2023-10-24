package com.example.bankapp.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private String id;
    private String status;
    private String taxCode;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}
