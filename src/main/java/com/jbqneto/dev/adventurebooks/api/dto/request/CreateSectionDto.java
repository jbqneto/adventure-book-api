package com.jbqneto.dev.adventurebooks.api.dto.request;

import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateSectionDto (
        @NotBlank
        String text,
        @NotNull
        SectionType type,
        @NotEmpty
        Set<CreateOptionDto> options
) {
}
