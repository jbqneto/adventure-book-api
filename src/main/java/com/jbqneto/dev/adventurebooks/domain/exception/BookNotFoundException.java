package com.jbqneto.dev.adventurebooks.domain.exception;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException() {
        super("Book not found");
    }
}
