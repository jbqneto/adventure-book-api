package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreateBookRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateConsequenceDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateOptionDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateSectionDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;
import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import com.jbqneto.dev.adventurebooks.domain.exception.*;
import com.jbqneto.dev.adventurebooks.domain.mapper.BookMapper;
import com.jbqneto.dev.adventurebooks.domain.model.Book;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Mock
    BookRepository bookRepository;

    BookService serviceUnderTest;

    @BeforeEach
    void setup() {

        serviceUnderTest = new BookService(bookRepository, bookMapper);
    }

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
                List.of(beginSection, middleSection(10, 2), middleSection(5,3), middleSection, endSection)
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
                List.of(middleSection(1, 3))
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
                List.of(beginSection(1), beginSection(2))
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
                List.of(beginSection(1), middleSection(2, 1))
        );

        //when & then
        Assertions.assertThrows(NonEndingException.class, () -> serviceUnderTest.createBook(bookRequest));
    }

    //Book has invalid next section id.
    @Test
    void shouldThrowErrorCreatingBookWithoutValidSectionId() {
        //given
        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1),
                List.of(beginSection(1), endSection(), middleSection(2, 300))
        );

        //when & then
        Assertions.assertThrows(InvalidSectionException.class, () -> serviceUnderTest.createBook(bookRequest));
    }

    //A non-ending section has no options
    @Test
    void shouldThrowErrorCreatingBookWithNodeSectionWithoutOptions() {
        //given
        var bookRequest = new CreateBookRequestDto(
                "J. R. R. Tolkien",
                "The Cave Adventure",
                DifficultyLevel.EASY,
                Set.of(1),
                List.of(beginSection(1), middleSectionWithoutOptions(), endSection(5))
        );

        //when & then
        Assertions.assertThrows(NoOptionException.class, () -> serviceUnderTest.createBook(bookRequest));
    }

    private CreateSectionDto endSection() {
        return endSection(3);
    }

    private CreateSectionDto endSection(int ref) {
        return new CreateSectionDto(
                ref,
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
                List.of(
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
                List.of()
        );
    }

    private static CreateSectionDto beginSection(int ref) {
        return new CreateSectionDto(
                ref,
                "You are standing at the entrance of a dark cave.",
                SectionType.BEGIN,
                List.of(
                        new CreateOptionDto(
                                "Enter the cave",
                                2,
                                null
                        )
                )
        );
    }
}