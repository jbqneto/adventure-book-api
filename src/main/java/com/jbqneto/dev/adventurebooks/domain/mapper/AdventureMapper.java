package com.jbqneto.dev.adventurebooks.domain.mapper;

import com.jbqneto.dev.adventurebooks.api.dto.response.AdventureResponseDto;
import com.jbqneto.dev.adventurebooks.domain.model.PlayerProgress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdventureMapper {

    AdventureResponseDto toResponseDto(PlayerProgress progress);
}
