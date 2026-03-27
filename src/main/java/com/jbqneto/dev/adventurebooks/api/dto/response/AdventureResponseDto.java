package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;

public record AdventureResponseDto(
        Long adventureId,
        Long playerId,
        String playerName,
        Long bookId,
        String bookTitle,
        ProgressStatus status,
        int health,
        GetSectionDto currentSection
) {}
