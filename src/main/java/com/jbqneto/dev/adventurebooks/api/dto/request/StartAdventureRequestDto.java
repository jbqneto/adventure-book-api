package com.jbqneto.dev.adventurebooks.api.dto.request;

public record StartAdventureRequestDto(
        Long playerId,
        Long bookId
) {}
