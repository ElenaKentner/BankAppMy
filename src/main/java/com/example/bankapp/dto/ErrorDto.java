package com.example.bankapp.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorDto {
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;
    private final String message;
    private final String details;

    public ErrorDto(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
