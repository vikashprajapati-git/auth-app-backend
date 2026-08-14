package com.auth.dtos;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public record ErrorResponse (
    HttpStatus status,
    String error,
    Timestamp timestamp
){}
