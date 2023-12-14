package com.example.bankapp.controller.exception;

import com.example.bankapp.dto.ErrorDTO;
import com.example.bankapp.exception.CurrencyCheckException;
import com.example.bankapp.exception.InvalidAmountException;
import com.example.bankapp.exception.NotEnoughMoneyException;
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
    public ResponseEntity<ErrorDTO> handlerRuntimeException(RuntimeException runtimeException) {
        ErrorDTO errorDto = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR,
                runtimeException.getMessage(),
                Arrays.toString(runtimeException.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException exception) {
        ErrorDTO errorDto = new ErrorDTO(HttpStatus.NO_CONTENT,
                exception.getMessage(),
                Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorDTO> handleNotEnoughMoneyException(NotEnoughMoneyException notEnoughMoneyException) {
        ErrorDTO errorDto = new ErrorDTO(HttpStatus.NOT_ACCEPTABLE,
                notEnoughMoneyException.getMessage(),
                Arrays.toString(notEnoughMoneyException.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({CurrencyCheckException.class, InvalidAmountException.class})
    public ResponseEntity<ErrorDTO> handleCurrencyCheckException(RuntimeException runtimeException) {
        ErrorDTO errorDto = new ErrorDTO(HttpStatus.BAD_REQUEST,
                runtimeException.getMessage(),
                Arrays.toString(runtimeException.getStackTrace()));
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorDTO errorDto = new ErrorDTO(HttpStatus.BAD_REQUEST,
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
