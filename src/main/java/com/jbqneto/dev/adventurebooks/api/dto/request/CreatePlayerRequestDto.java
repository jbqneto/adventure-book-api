package com.jbqneto.dev.adventurebooks.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePlayerRequestDto(
        @NotBlank
        @Size(max = 80)
        String username
) { }