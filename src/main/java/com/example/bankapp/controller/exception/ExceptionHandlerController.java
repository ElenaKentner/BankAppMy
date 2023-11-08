package com.example.bankapp.controller.exception;

import com.example.bankapp.dto.ErrorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handlerRuntimeException(RuntimeException runtimeException) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR,
                runtimeException.getMessage(),
                Arrays.toString(runtimeException.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.NO_CONTENT,
                exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST,
                getMessage(exception),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    private String getMessage(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return fieldErrors.stream()
                .findAny()
                .orElseThrow(NoSuchFieldError::new)
                .getDefaultMessage();
    }
}
