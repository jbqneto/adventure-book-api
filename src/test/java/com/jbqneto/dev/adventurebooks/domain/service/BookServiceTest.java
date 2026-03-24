package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreateBookRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateConsequenceDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateOptionDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateSectionDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;
import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import com.jbqneto.dev.adventurebooks.domain.model.Book;
import com.jbqneto.dev.adventurebooks.domain.model.Section;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService serviceUnderTest;

    @Test
    void shouldCreateBook() {
        var beginSection = new CreateSectionDto(
                1,
                "You are standing at the entrance of a dark cave.",
                SectionType.BEGIN,
                Set.of(
                        new CreateOptionDto(
                                "Enter the cave",
                                2,
                                null
                        )
                )
        );

        var middleSection = new CreateSectionDto(
                2,
                "You move deeper into the cave and hear strange noises.",
                SectionType.NODE,
                Set.of(
                        new CreateOptionDto(
                                "Open the ancient chest",
                                3,
                                new CreateConsequenceDto(
                                        ConsequenceType.LOSE_HEALTH,
                                        "A trap hurts you.",
                                        2
                                )
                        ),
                        new CreateOptionDto(
                                "Ignore the chest and keep walking",
                                3,
                                null
                        )
                )
        );

        var endSection = new CreateSectionDto(
                3,
                "You found the treasure and escaped.",
                SectionType.END,
                null
        );

        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1, 2),
                Set.of()
        );

        var book = serviceUnderTest.createBook(bookRequest);

        Mockito.verify(bookRepository).save(Mockito.any(Book.class));
    }

    //Book has none beginning
    @Test
    void shouldThrowExceptionWhenBookHasNoBeginning() {

    }


    //Book has more than one beginning
    @Test
    void shouldThrowExceptionWhenBookHasMultipleBeginning() {

    }

    //A book has an author and a difficulty level
    @Test
    void shouldThrowErrorCreatingBookWithoutAuthor() {

    }

    @Test
    void shouldThrowErrorCreatingBookWithoutLevel() {

    }

    //Book has no ending
    @Test
    void shouldThrowErrorCreatingBookWithoutEnding() {

    }

    //Book has invalid next section id.
    @Test
    void shouldThrowErrorCreatingBookWithoutInvalidSectionId() {

    }

    //A non-ending section has no options
    @Test
    void shouldThrowErrorCreatingBookWithoutNoEndingSectionWithoutOptions() {

    }

}