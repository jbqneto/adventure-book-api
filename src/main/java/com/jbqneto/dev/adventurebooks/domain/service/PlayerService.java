package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreatePlayerRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetPlayersResponseDto;
import com.jbqneto.dev.adventurebooks.domain.exception.PlayerNotFoundException;
import com.jbqneto.dev.adventurebooks.domain.model.Player;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public List<GetPlayersResponseDto> getAll() {
        return playerRepository.findAll()
                .stream()
                .map(p -> new GetPlayersResponseDto(p.getId(), p.getUsername()))
                .toList();
    }

    public GetPlayersResponseDto create(CreatePlayerRequestDto request) {
        var existing = playerRepository.findByUsername(request.username());

        if (existing.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        var player = new Player();
        player.setUsername(request.username());

        var saved = playerRepository.save(player);

        return new GetPlayersResponseDto(saved.getId(), saved.getUsername());
    }

    public void delete(Long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new PlayerNotFoundException();
        }

        playerRepository.deleteById(playerId);
    }
}
