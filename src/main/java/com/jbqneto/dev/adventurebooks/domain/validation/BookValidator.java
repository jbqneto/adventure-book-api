package com.jbqneto.dev.adventurebooks.domain.validation;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreateBookRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateOptionDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateSectionDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import com.jbqneto.dev.adventurebooks.domain.exception.*;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class BookValidator {

    public static void validateBook(CreateBookRequestDto book) {
        validateBookWithOneBegining(book);
        validateBookHasEnding(book);
        validateBookSections(book);
    }

    private static void validateBookSections(CreateBookRequestDto book) {
        Set<Long> sections = new HashSet<>();
        Set<CreateOptionDto> options = new HashSet<>();

        for (CreateSectionDto section : book.sections()) {
            sections.add(section.id());

            if (section.type() != SectionType.END) {
                validateNonEndingSection(section);
            }

            if (!CollectionUtils.isEmpty(section.options())) {
                options.addAll(section.options());
            }
        }

        options.forEach(option -> {
            if (!sections.contains(option.gotoId())) {
                throw new InvalidSectionException(
                        "Option is referencing to inexistent section: %s".formatted(option.gotoId())
                );
            }
        });

    }

    private static void validateNonEndingSection(CreateSectionDto section) {
        if (section.options() == null || section.options().isEmpty()) {
            throw new NoOptionException("Section %s has no options".formatted(section.id()));
        }

        if (section.options().size() == 1 && section.options().getFirst().gotoId() == section.id()) {
            throw new InvalidSectionException("Infinite loop section");
        }
    }

    private static void validateBookWithOneBegining(CreateBookRequestDto book) {
        long beginnings = book.sections()
                .stream()
                .filter(sec -> sec.type() == SectionType.BEGIN)
                .count();

        if (beginnings == 0) {
            throw new NoBeginningException();
        }

        if (beginnings > 1) {
            throw new MultipleBeginningException();
        }
    }

    private static void validateBookHasEnding(CreateBookRequestDto book) {
        boolean hasNoENd = book.sections().stream().noneMatch(sec -> sec.type() == SectionType.END);

        if (hasNoENd) {
            throw new NonEndingException();
        }
    }
}
