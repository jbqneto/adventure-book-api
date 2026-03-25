package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.StartAdventureRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.AdventureResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetOptionDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetSectionDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;
import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import com.jbqneto.dev.adventurebooks.domain.exception.*;
import com.jbqneto.dev.adventurebooks.domain.mapper.AdventureMapper;
import com.jbqneto.dev.adventurebooks.domain.model.*;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.BookRepository;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.PlayerProgressRepository;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AdventureService {

    private final BookRepository bookRepository;
    private final PlayerRepository playerRepository;
    private final PlayerProgressRepository playerProgressRepository;

    private final AdventureMapper adventureMapper;

    public AdventureResponseDto choose(Long adventureId, Long optionId) {
        PlayerProgress progress = playerProgressRepository.findById(adventureId)
                .orElseThrow(AdventureNotFoundException::new);

        Section currentSection = progress.getCurrentSection();

        var option = currentSection.getOptions().stream()
                .filter(opt -> Objects.equals(opt.getId(), optionId))
                .findFirst()
                .orElseThrow(() -> new OptionNotFoundException("Option for found for current section"));

        Section nextSection = option.getNextSection();
        Consequence consequence = option.getConsequence();
        int health = progress.getHealth();

        //Objective 4: Handle the consequences mechanism for a player.
        //TODO: Consequence handler (strategy)
        if (consequence != null) {
            switch (consequence.getType()) {
                case LOSE_HEALTH -> health -= consequence.getValue();
                case GAIN_HEALTH -> health += consequence.getValue();
                case DIE -> health = 0;
            }

        }

        if (health == 0) {
            progress.setStatus(ProgressStatus.DEAD);
        }


        //TODO: Calculate consequence on this choice properly

        return new AdventureResponseDto(
                adventureId,
                progress.getPlayer().getId(),
                progress.getPlayer().getUsername(),
                progress.getBook().getId(),
                progress.getBook().getTitle(),
                progress.getHealth(),
                new GetSectionDto(
                        nextSection.getId(),
                        nextSection.getText(),
                        nextSection.getOptions().stream()
                                .map(opt -> new GetOptionDto(opt.getId(), opt.getDescription(), opt.getNextSection().getReference()))
                                .toList()
                )

        );
    }

    public AdventureResponseDto start(StartAdventureRequestDto request) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(BookNotFoundException::new);

        Player player = playerRepository.findById(request.playerId())
                .orElseThrow(PlayerNotFoundException::new);

        PlayerProgress playerProgress = createNewAdventure(book, player);

        Section beginSection = book.getSections().stream()
                .filter(sec -> sec.getType() == SectionType.BEGIN)
                .findFirst()
                .orElseThrow(() -> new SectionNotFoundException("Begin section not found"));

        return new AdventureResponseDto(
                playerProgress.getId(),
                player.getId(),
                player.getUsername(),
                book.getId(),
                book.getTitle(),
                playerProgress.getHealth(),
                new GetSectionDto(beginSection.getId(), beginSection.getText(), List.of())
        );
    }

    private PlayerProgress createNewAdventure(Book book, Player player) {
        PlayerProgress playerProgress = new PlayerProgress();
        playerProgress.setPlayer(player);
        playerProgress.setBook(book);
        playerProgress.setStatus(ProgressStatus.IN_PROGRESS);

        return playerProgressRepository.save(playerProgress);
    }

    //TODO:
    public AdventureResponseDto get(Long adventureId) {
        return null;
    }
}
