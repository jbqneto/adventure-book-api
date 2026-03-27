package com.jbqneto.dev.adventurebooks.api.dto.response;

public record AdventureSummaryDto(
        long bookId,
        String title,
        long currentSection,
        int health
) {
}
