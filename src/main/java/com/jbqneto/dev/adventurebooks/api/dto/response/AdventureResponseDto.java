package com.jbqneto.dev.adventurebooks.api.dto.response;

public record AdventureResponseDto(
        Long adventureId,
        Long playerId,
        String playerName,
        Long bookId,
        String bookTitle,
        int health,
        GetSectionDto currentSection
) {}
