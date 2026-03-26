package com.jbqneto.dev.adventurebooks.domain.exception;

public class CategoryAlreadyExistsException extends ResourceAlreadyExistsException {
    public CategoryAlreadyExistsException() {
        super("Category already exists!");
    }
}
