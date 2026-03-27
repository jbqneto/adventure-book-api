package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.StartAdventureRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.AdventureResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetOptionDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetSectionDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;
import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import com.jbqneto.dev.adventurebooks.domain.exception.*;
import com.jbqneto.dev.adventurebooks.domain.handler.ConsequenceRegistry;
import com.jbqneto.dev.adventurebooks.domain.mapper.AdventureMapper;
import com.jbqneto.dev.adventurebooks.domain.model.*;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.BookRepository;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.PlayerProgressRepository;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.PlayerRepository;
import jakarta.transaction.Transactional;
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
    private final ConsequenceRegistry consequenceRegistry;

    private final AdventureMapper adventureMapper;

    @Transactional
    public AdventureResponseDto choose(Long adventureId, Long optionId) {
        PlayerProgress progress = playerProgressRepository.findById(adventureId)
                .orElseThrow(AdventureNotFoundException::new);

        if (progress.getStatus() != ProgressStatus.IN_PROGRESS) {
            throw new BusinessViolationException("You can only move forward with an active story");
        }

        Section currentSection = progress.getCurrentSection();

        var option = currentSection.getOptions().stream()
                .filter(opt -> Objects.equals(opt.getId(), optionId))
                .findFirst()
                .orElseThrow(() -> new OptionNotFoundException("Option for found for current section"));

        Section nextSection = option.getNextSection();
        Consequence consequence = option.getConsequence();

        //Objective 4: Handle the consequence mechanism for a player.
        if (consequence != null) {
            consequenceRegistry.get(consequence).apply(progress, option);
        }

        GetSectionDto nextSectionResponse = null;
        int health = progress.getHealth();

        if (health == 0) {
            progress.setStatus(ProgressStatus.DEAD);
            playerProgressRepository.delete(progress);
        } else {

            progress.setCurrentSection(nextSection);

            playerProgressRepository.save(progress);

            nextSectionResponse = new GetSectionDto(
                    nextSection.getId(),
                    nextSection.getText(),
                    nextSection.getOptions().stream()
                            .map(opt -> new GetOptionDto(opt.getId(), opt.getDescription(), opt.getNextSection().getReference()))
                            .toList()
            );
        }

        return new AdventureResponseDto(
                adventureId,
                progress.getPlayer().getId(),
                progress.getPlayer().getUsername(),
                progress.getBook().getId(),
                progress.getBook().getTitle(),
                progress.getStatus().name(),
                progress.getHealth(),
                nextSectionResponse
        );
    }

    public AdventureResponseDto start(StartAdventureRequestDto request) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(BookNotFoundException::new);

        Player player = playerRepository.findById(request.playerId())
                .orElseThrow(PlayerNotFoundException::new);

        if (playerProgressRepository.existsByPlayerIdAndBookId(request.playerId(), request.bookId())) {
            throw new BusinessViolationException("This player already started this adventure");
        }

        PlayerProgress playerProgress = createNewAdventure(book, player);

        Section beginSection = book.getSections().stream()
                .filter(sec -> sec.getType() == SectionType.BEGIN)
                .findFirst()
                .orElseThrow(() -> new SectionNotFoundException("Begin section not found"));

        return adventureMapper.toResponseDto(playerProgress);
    }

    private PlayerProgress createNewAdventure(Book book, Player player) {
        PlayerProgress playerProgress = new PlayerProgress();
        playerProgress.setPlayer(player);
        playerProgress.setBook(book);
        playerProgress.setCurrentSection(book.getBeginningSection());
        playerProgress.setStatus(ProgressStatus.IN_PROGRESS);

        return playerProgressRepository.save(playerProgress);
    }

    //TODO:
    public AdventureResponseDto get(Long adventureId) {
        PlayerProgress progress = playerProgressRepository.findById(adventureId)
                .orElseThrow(AdventureNotFoundException::new);

        return adventureMapper.toResponseDto(progress);
    }
}
