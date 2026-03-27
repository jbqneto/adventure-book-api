package com.jbqneto.dev.adventurebooks.api.controller;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreatePlayerRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetPlayerAdventuresResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetPlayersResponseDto;
import com.jbqneto.dev.adventurebooks.domain.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<GetPlayersResponseDto> getAllPlayers() {
        return playerService.getAll();
    }

    @PostMapping
    public GetPlayersResponseDto createPlayer(@RequestBody CreatePlayerRequestDto request) {
        return playerService.create(request);
    }

    @DeleteMapping("/{playerId}")
    public void deletePlayer(@PathVariable Long playerId) {
        playerService.delete(playerId);
    }

    @GetMapping("/{playerId}/adventures")
    public GetPlayerAdventuresResponseDto getPlayerActiveAdvendures(@PathVariable Long playerId) {

        return playerService.getActiveAdventures(playerId);
    }
}
