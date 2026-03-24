package com.jbqneto.dev.adventurebooks.api.dto.request;

import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateSectionDto (
        @Size(min = 1)
        int reference,
        @NotBlank
        String text,
        @NotNull
        SectionType type,

        List<CreateOptionDto> options
) { }
