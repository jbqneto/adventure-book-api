package com.jbqneto.dev.adventurebooks.domain.mapper;

import com.jbqneto.dev.adventurebooks.api.dto.response.AdventureResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.OptionResponseDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;
import com.jbqneto.dev.adventurebooks.domain.model.Option;
import com.jbqneto.dev.adventurebooks.domain.model.PlayerProgress;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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

    @Mapping(target = "optionId", source = "id")
    @Mapping(target = "description", source = "description")
    OptionResponseDto toOptionResponseDto(Option option);

    List<OptionResponseDto> toOptionResponseDtoList(List<Option> options);
}