package com.jbqneto.dev.adventurebooks.domain.mapper;

import com.jbqneto.dev.adventurebooks.api.dto.response.AdventureResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.GetOptionDto;
import com.jbqneto.dev.adventurebooks.domain.model.Option;
import com.jbqneto.dev.adventurebooks.domain.model.PlayerProgress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdventureMapper {

    @Mapping(target = "adventureId", source = "id")
    @Mapping(target = "playerId", source = "player.id")
    @Mapping(target = "playerName", source = "player.username")
    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    @Mapping(target = "health", source = "health")
    @Mapping(target = "status", expression = "java(progress.getStatus().name())")
    AdventureResponseDto toResponseDto(PlayerProgress progress);

    @Mapping(target = "description", source = "description")
    @Mapping(target = "gotoId", source = "nextSection.id")
    GetOptionDto toOptionResponseDto(Option option);

    List<GetOptionDto> toOptionResponseDtoList(List<Option> options);
}