package com.auth.exceptions;


import com.auth.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        ErrorResponse resourceNotFound= new ErrorResponse(HttpStatus.NOT_FOUND, resourceNotFoundException.getMessage(), Timestamp.from(Instant.now()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFound);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        ErrorResponse resourceNotFound= new ErrorResponse(HttpStatus.BAD_REQUEST, illegalArgumentException.getMessage(), Timestamp.from(Instant.now()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceNotFound);
    }

}
