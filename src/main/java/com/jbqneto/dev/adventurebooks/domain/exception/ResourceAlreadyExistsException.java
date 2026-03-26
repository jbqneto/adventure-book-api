package com.jbqneto.dev.adventurebooks.domain.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException() {
        this("Resource already exists");
    }
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
