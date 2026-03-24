package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.AddCategoriesRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateBookRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookCreatedResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookDetailsResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookSummaryResponseDto;
import com.jbqneto.dev.adventurebooks.domain.mapper.BookMapper;
import com.jbqneto.dev.adventurebooks.domain.validation.BookValidator;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookCreatedResponseDto createBook(CreateBookRequestDto bookDto) {
        BookValidator.validateBook(bookDto);

        var book = bookMapper.toEntity(bookDto);

        return bookMapper.toCreatedResponse(bookRepository.save(book));
    }

    public List<BookSummaryResponseDto> getBooks(
            String title,
            String author,
            String category,
            String difficulty
    ) {
        return Collections.emptyList();
    }

    public BookDetailsResponseDto getBookById(Long bookId) {
        return null;
    }

    public void addCategory(Long bookId, AddCategoriesRequestDto request) {
        
    }

    public void removeCategory(Long bookId, Integer categoryId) {
    }
}
