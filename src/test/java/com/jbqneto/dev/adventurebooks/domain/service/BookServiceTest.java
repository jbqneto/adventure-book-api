package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreateBookRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateConsequenceDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateOptionDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateSectionDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;
import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import com.jbqneto.dev.adventurebooks.domain.exception.*;
import com.jbqneto.dev.adventurebooks.domain.model.Book;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService serviceUnderTest;

    @Test
    void shouldCreateBook() {
        var beginSection = beginSection(1);
        var middleSection = middleSection(2, 3);
        var endSection = endSection();

        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1, 2),
                Set.of(beginSection, middleSection, endSection)
        );

        var book = serviceUnderTest.createBook(bookRequest);

        Mockito.verify(bookRepository).save(Mockito.any(Book.class));
    }

    //Book has none beginning
    @Test
    void shouldThrowExceptionWhenBookHasNoBeginning() {
        //given
        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1, 2),
                Set.of(middleSection(1, 3))
        );

        //when & then
        Assertions.assertThrows(NoBeginningException.class, () -> serviceUnderTest.createBook(bookRequest));

    }


    //Book has more than one beginning
    @Test
    void shouldThrowExceptionWhenBookHasMultipleBeginning() {
        //given
        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1),
                Set.of(beginSection(1), beginSection(2))
        );

        //when & then
        Assertions.assertThrows(MultipleBeginningException.class, () -> serviceUnderTest.createBook(bookRequest));
    }

    //Book has no ending
    @Test
    void shouldThrowErrorCreatingBookWithoutEnding() {
        //given
        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1),
                Set.of(beginSection(1), middleSection(2, 1))
        );

        //when & then
        Assertions.assertThrows(NonEndingException.class, () -> serviceUnderTest.createBook(bookRequest));
    }

    //Book has invalid next section id.
    @Test
    void shouldThrowErrorCreatingBookWithoutInvalidSectionId() {
        //given
        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1),
                Set.of(beginSection(1), middleSection(2, 3))
        );

        //when & then
        Assertions.assertThrows(InvalidSectionException.class, () -> serviceUnderTest.createBook(bookRequest));
    }

    //A non-ending section has no options
    @Test
    void shouldThrowErrorCreatingBookWithoutNoEndingSectionWithoutOptions() {
        //given
        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1),
                Set.of(beginSection(1), middleSectionWithoutOptions(), endSection())
        );

        //when & then
        Assertions.assertThrows(NoOptionException.class, () -> serviceUnderTest.createBook(bookRequest));
    }

    private CreateSectionDto endSection() {
        return new CreateSectionDto(
                3,
                "You found the treasure and escaped.",
                SectionType.END,
                null
        );
    }

    private CreateSectionDto middleSection(int ref, int nextSection) {
        return new CreateSectionDto(
                ref,
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
                                nextSection,
                                null
                        )
                )
        );
    }

    private CreateSectionDto middleSectionWithoutOptions() {
        return new CreateSectionDto(
                1,
                "You will now run into an error. hahahaha!!",
                SectionType.NODE,
                Set.of()
        );
    }

    private static CreateSectionDto beginSection(int ref) {
        return new CreateSectionDto(
                ref,
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
    }
}