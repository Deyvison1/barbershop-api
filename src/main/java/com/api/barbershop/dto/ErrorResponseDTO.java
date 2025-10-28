package com.api.barbershop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ErrorResponseDTO {
    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
}
