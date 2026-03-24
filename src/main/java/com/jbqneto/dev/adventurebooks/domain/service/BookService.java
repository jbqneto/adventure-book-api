package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreateBookRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.BookCreatedResponseDto;
import com.jbqneto.dev.adventurebooks.domain.model.Book;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookCreatedResponseDto createBook(CreateBookRequestDto request) {

        return null;
    }

}
