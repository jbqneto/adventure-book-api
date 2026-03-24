package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;

import java.util.Set;

public record BookSummaryResponseDto(
        Long id,
        String title,
        String author,
        DifficultyLevel difficulty,
        Set<String> categories
) {
}
