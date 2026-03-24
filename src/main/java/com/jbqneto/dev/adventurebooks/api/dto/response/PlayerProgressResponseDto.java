package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;

public record PlayerProgressResponseDto(
        Long playerId,
        String username,
        Long bookId,
        String bookTitle,
        Long currentSectionId,
        Integer currentSectionReferenceId,
        int health,
        ProgressStatus status
) {
}
