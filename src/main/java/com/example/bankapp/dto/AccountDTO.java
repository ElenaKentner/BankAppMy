package com.example.bankapp.dto;

import com.example.bankapp.entity.Status;
import com.example.bankapp.entity.Type;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Data
public class AccountDTO {
    private UUID id;
    private UUID clientId;
    private Status status;
    private Integer currencyCode;
    private Type type;
    private Double balance;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
