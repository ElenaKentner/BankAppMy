package com.example.bankapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClientDTO {
    private String id;
    private String status;
    private String taxCode;

    @NotBlank
    @Pattern(regexp = "[A-Za-z-']", message = "first name must contain only latin alphabet")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "[A-Za-z-']", message = "last name must contain only latin alphabet")
    private String lastName;

    @Email
    private String email;

    private String address;
    private String phone;
}
