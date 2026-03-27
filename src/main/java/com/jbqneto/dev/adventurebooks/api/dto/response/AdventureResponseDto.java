package com.jbqneto.dev.adventurebooks.api.dto.response;

public record AdventureResponseDto(
        Long adventureId,
        Long playerId,
        String playerName,
        Long bookId,
        String bookTitle,
        String status,
        int health,
        GetSectionDto currentSection
) {}
