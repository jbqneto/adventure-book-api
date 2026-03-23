package com.jbqneto.dev.adventurebooks.api.dto.request;

import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateConsequenceDto(
        @NotNull
        ConsequenceType type,
        @NotBlank
        @Size(min = 5, max = 150)
        String text,
        int value
) {
}
