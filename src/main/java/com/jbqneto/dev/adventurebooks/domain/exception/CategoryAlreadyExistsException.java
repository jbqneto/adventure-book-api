package com.jbqneto.dev.adventurebooks.domain.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super("Category already exists!");
    }
}
