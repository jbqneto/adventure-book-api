package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;

import java.util.List;
import java.util.Set;

public record BookDetailsResponseDto(
        Long id,
        String title,
        String author,
        DifficultyLevel difficulty,
        Set<String> categories,
        List<SectionSummaryDto> sections
) {
}
