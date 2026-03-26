package com.jbqneto.dev.adventurebooks.domain.exception;

public class InvalidSectionException extends BusinessViolationException {
    public InvalidSectionException(String message) {
        super(message);
    }
}
