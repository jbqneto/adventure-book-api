package com.jbqneto.dev.adventurebooks.api.dto.request;

import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;
import com.jbqneto.dev.adventurebooks.domain.model.Section;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateBookRequestDto(
        @NotBlank
        @Size(max = 120, min = 5)
        String author,
        @NotBlank
        @Size(max = 120, min = 5)
        String title,
        @NotNull
        DifficultyLevel difficulty,

        @NotEmpty
        Set<Integer> categories,

        @Valid
        @NotEmpty
        Set<CreateSectionDto> sections
) {
}
