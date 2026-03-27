package com.jbqneto.dev.adventurebooks.api.controller;

import com.jbqneto.dev.adventurebooks.api.dto.request.StartAdventureRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.AdventureResponseDto;
import com.jbqneto.dev.adventurebooks.domain.service.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/adventures")
public class AdventureController {

    private final AdventureService adventureService;

    /**
     * Start a new adventure (create PlayerProgress)
     */
    @PostMapping
    public AdventureResponseDto startAdventure(
            @RequestBody StartAdventureRequestDto request
    ) {
        return adventureService.start(request);
    }

    @GetMapping("/{adventureId}")
    public AdventureResponseDto getAdventure(
            @PathVariable Long adventureId
    ) {
        return adventureService.get(adventureId);
    }

    /**
     * Choose an option and advance the story
     */
    @PostMapping("/{adventureId}/choices/{choiceId}")
    public AdventureResponseDto chooseOption(
            @PathVariable Long adventureId,
            @PathVariable Long choiceId
    ) {
        return adventureService.choose(adventureId, choiceId);
    }
}
