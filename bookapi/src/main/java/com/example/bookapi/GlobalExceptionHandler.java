package com.example.bookapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField()  + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value()));
    }
    public static class ErrorResponse{
        private List<String> errors;
        private int status;

        public ErrorResponse(List<String> errors, int status){
            this.errors = errors;
            this.status= status;
        }
        public List<String> getErrors(){
            return errors;
        }
        public int getStatus(){
            return status;
        }
    }
}
