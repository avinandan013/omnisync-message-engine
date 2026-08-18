package com.omnisync.message_engine.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String message,
        HttpStatus error,
        int status
) {
}
