package com.jbqneto.dev.adventurebooks.domain.exception;

public class InvalidSectionException extends RuntimeException {
    public InvalidSectionException(String message) {
        super(message);
    }
}
