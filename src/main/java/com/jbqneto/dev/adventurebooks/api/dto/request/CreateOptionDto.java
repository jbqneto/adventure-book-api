package com.jbqneto.dev.adventurebooks.api.dto.request;

import jakarta.validation.constraints.Size;

public record CreateOptionDto(

        @Size(max = 200, min = 10)
        String description,
        int nextSectionReference
) {
}
