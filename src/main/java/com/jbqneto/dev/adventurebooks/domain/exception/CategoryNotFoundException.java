package com.jbqneto.dev.adventurebooks.domain.exception;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(Long id) {
        this("Category not found: " + id);
    }

    public CategoryNotFoundException() {
        this("Category not found!");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
