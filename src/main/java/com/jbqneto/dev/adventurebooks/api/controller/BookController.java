package com.jbqneto.dev.adventurebooks.api.controller;

import com.jbqneto.dev.adventurebooks.api.dto.request.AddCategoriesRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.request.CreateBookRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookCreatedResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookDetailsResponseDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookSummaryResponseDto;
import com.jbqneto.dev.adventurebooks.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookSummaryResponseDto> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String difficulty
    ) {
        return bookService.getBooks(title, author, category, difficulty);
    }

    // Objective 2 - get book details
    @GetMapping("/{bookId}")
    public BookDetailsResponseDto getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public BookCreatedResponseDto createBook(@RequestBody CreateBookRequestDto request) {
        return bookService.createBook(request);
    }

    // Objective 2 - add category
    @PostMapping("/{bookId}/categories")
    public void addCategory(
            @PathVariable Long bookId,
            @RequestBody AddCategoriesRequestDto request
    ) {
        bookService.addCategory(bookId, request);
    }

    // Objective 2 - remove category
    @DeleteMapping("/{bookId}/categories/{categoryId}")
    public void removeCategory(
            @PathVariable Long bookId,
            @PathVariable Integer categoryId
    ) {
        bookService.removeCategory(bookId, categoryId);
    }
}
