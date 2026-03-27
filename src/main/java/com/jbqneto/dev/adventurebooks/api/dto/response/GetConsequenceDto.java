package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import jakarta.annotation.Nullable;

public record GetConsequenceDto(
        long id,
        ConsequenceType type,
        @Nullable
        Integer value
) {
}
