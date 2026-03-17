package com.example.bookapi;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, Object> errors = new HashMap<>();

        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", ex.getBindingResult()
                .getFieldErrors().stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }


}
