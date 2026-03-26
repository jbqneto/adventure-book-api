package com.jbqneto.dev.adventurebooks.domain.exception;

public class NotFoundException extends BusinessViolationException {
    public NotFoundException(String message) {
        super(message);
    }
}
