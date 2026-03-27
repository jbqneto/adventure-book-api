package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.domain.model.Player;

public record GetPlayersResponseDto(
        Long id,
        String username
) {

    public GetPlayersResponseDto(Player player) {
        this(player.getId(), player.getUsername());
    }
}
