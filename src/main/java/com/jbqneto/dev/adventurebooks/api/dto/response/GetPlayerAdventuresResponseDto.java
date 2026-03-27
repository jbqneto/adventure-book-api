package com.jbqneto.dev.adventurebooks.api.dto.response;

import java.util.List;

public record GetPlayerAdventuresResponseDto(
        long playerId,
        String username,
        List<AdventureSummaryDto> adventures
) {
}
