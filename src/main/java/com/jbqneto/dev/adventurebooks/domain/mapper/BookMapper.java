package com.jbqneto.dev.adventurebooks.domain.mapper;

import com.jbqneto.dev.adventurebooks.api.dto.request.*;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookCreatedResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookSummaryResponseDto;
import com.jbqneto.dev.adventurebooks.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "sections", source = "sections", qualifiedByName = "mapAndSortSections")
    Book toEntity(CreateBookRequestDto request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "book", ignore = true)
    Section toEntity(CreateSectionDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "nextSection", source = "nextSectionReference", qualifiedByName = "mapNextSection")
    @Mapping(target = "consequence", source = "consequence")
    Option toEntity(CreateOptionDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "option", ignore = true)
    Consequence toEntity(CreateConsequenceDto dto);

    BookSummaryResponseDto toSummary(Book book);

    List<BookSummaryResponseDto> toSummaryList(List<Book> books);


    @Named("mapNextSection")
    default Section mapNextSection(int nextSectionReference) {
        var nextSection = new Section();
        nextSection.setReference(nextSectionReference);

        return nextSection;
    }

    @Named("mapAndSortSections")
    default List<Section> mapAndSortSections(List<CreateSectionDto> dtos) {
        if (dtos == null) {
            return List.of();
        }

        return dtos.stream()
                .sorted(Comparator.comparing(CreateSectionDto::reference))
                .map(this::toEntity)
                .toList();
    }

    List<Option> toOptionEntities(List<CreateOptionDto> dtos);

    BookCreatedResponseDto toCreatedResponse(Book book);
}
