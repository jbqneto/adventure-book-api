package com.jbqneto.dev.adventurebooks.api.dto.error;

import java.time.Instant;

public record ApiErrorResponseDto(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
