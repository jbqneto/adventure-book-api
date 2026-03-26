package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.AddCategoriesRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateBookRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookCreatedResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookDetailsResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookSummaryResponseDto;
import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;
import com.jbqneto.dev.adventurebooks.domain.exception.BookNotFoundException;
import com.jbqneto.dev.adventurebooks.domain.exception.CategoryNotFoundException;
import com.jbqneto.dev.adventurebooks.domain.exception.InvalidSectionException;
import com.jbqneto.dev.adventurebooks.domain.exception.ResourceAlreadyExistsException;
import com.jbqneto.dev.adventurebooks.domain.mapper.BookMapper;
import com.jbqneto.dev.adventurebooks.domain.model.Book;
import com.jbqneto.dev.adventurebooks.domain.model.Category;
import com.jbqneto.dev.adventurebooks.domain.model.Option;
import com.jbqneto.dev.adventurebooks.domain.model.Section;
import com.jbqneto.dev.adventurebooks.domain.validation.BookValidator;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.BookRepository;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Transactional
    public BookCreatedResponseDto createBook(CreateBookRequestDto bookDto) {
        BookValidator.validateBook(bookDto);

        var existingBook = bookRepository.findBookByTitleAndAuthor(bookDto.title(), bookDto.author());

        if (existingBook.isPresent()) {
            throw new ResourceAlreadyExistsException("A book with the same title and author already registered");
        }

        Book book = bookMapper.toEntity(bookDto);


        List<Category> categories = categoryRepository.findAllById(bookDto.categories());

        if (categories.size() != bookDto.categories().size()) {
            throw new CategoryNotFoundException("One or more categories were not found");
        }

        book.setCategories(categories);
        Map<Long, Section> sectionsByRef = new HashMap<>();

        for (var section : book.getSections()) {
            section.setBook(book);
            sectionsByRef.put(section.getReference(), section);
        }

        for (var section : book.getSections()) {
            if (section.getOptions() == null) continue;

            for (var option : section.getOptions()) {
                option.setSection(section);

                if (option.getConsequence() != null) {
                    option.getConsequence().setOption(option);
                }
            }
        }

        List<Option> options = book.getSections()
                .stream()
                .flatMap(section -> section.getOptions().stream())
                .toList();

        for (Option option: options) {

            var target = sectionsByRef.get(option.getNextSection().getReference());

            //Double-checking (Already validated)
            if (target == null) {
                throw new InvalidSectionException(
                        "Section %s has an option pointing to invalid section %s"
                                .formatted(option.getSection().getReference(), option.getNextSection().getReference())
                );
            }

            option.setNextSection(target);

        }

        var saved = bookRepository.save(book);

        log.info("Saved data: {}", saved);

        return bookMapper.toCreatedResponse(saved);
    }

    public List<BookSummaryResponseDto> getBooks(
            String title,
            String author,
            String category,
            String difficulty
    ) {

        DifficultyLevel difficultyEnum = null;

        if (difficulty != null) {
            try {
                difficultyEnum = DifficultyLevel.valueOf(difficulty.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
            }
        }

        var books = bookRepository.findBooksWithFilters(
                title,
                author,
                category,
                difficultyEnum
        );

        return bookMapper.toSummaryList(books);
    }

    public BookDetailsResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        return bookMapper.toDetailsResponse(book);
    }

    @Transactional
    public void addCategory(Long bookId, AddCategoriesRequestDto request) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        List<Category> categories = categoryRepository.findAllById(request.categories());

        if (categories.size() != request.categories().size()) {
            throw new CategoryNotFoundException("Not all categories where found.");
        }

        for (Category category: categories) {
            if (!book.getCategories().contains(category)) {
                book.getCategories().add(category);
            }
        }

    }

    @Transactional
    public void removeCategory(Long bookId, Long categoryId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        List<Category> categories = book.getCategories().stream()
                .filter(cat -> !Objects.equals(cat.getId(), categoryId))
                .toList();

        book.setCategories(categories);
    }
}
