package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreatePlayerRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.AdventureSummaryDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetPlayerAdventuresResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetPlayersResponseDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;
import com.jbqneto.dev.adventurebooks.domain.exception.PlayerNotFoundException;
import com.jbqneto.dev.adventurebooks.domain.model.Player;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.PlayerProgressRepository;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerProgressRepository playerProgressRepository;

    public List<GetPlayersResponseDto> getAll() {
        return playerRepository.findAll()
                .stream()
                .map(p -> new GetPlayersResponseDto(p.getId(), p.getUsername()))
                .toList();
    }

    public GetPlayersResponseDto create(CreatePlayerRequestDto request) {
        return null;
    }

    public void delete(Long playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new PlayerNotFoundException();
        }

        playerRepository.deleteById(playerId);
    }

    public GetPlayerAdventuresResponseDto getActiveAdventures(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(PlayerNotFoundException::new);

        List<AdventureSummaryDto> adventures = playerProgressRepository.findbyPlayerIdAndStatus(playerId, ProgressStatus.IN_PROGRESS)
                .stream()
                .map(progress -> new AdventureSummaryDto(
                        progress.getBook().getId(),
                        progress.getBook().getTitle(),
                        progress.getCurrentSection().getId(),
                        progress.getHealth()
                )).toList();

        return new GetPlayerAdventuresResponseDto(
                playerId,
                player.getUsername(),
                adventures
        );
    }
}
