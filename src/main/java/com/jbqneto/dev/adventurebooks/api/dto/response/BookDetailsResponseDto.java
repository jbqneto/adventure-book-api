package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.api.dto.request.GetCategoryDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;

import java.util.List;
import java.util.Set;

public record BookDetailsResponseDto(
        Long id,
        String title,
        String author,
        DifficultyLevel difficulty,
        Set<GetCategoryDto> categories,
        List<SectionSummaryDto> sections
) {
}
