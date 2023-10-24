package com.example.bankapp.controller.exception;

import com.example.bankapp.dto.ErrorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.NO_CONTENT,
                exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.NO_CONTENT);
    }
}
