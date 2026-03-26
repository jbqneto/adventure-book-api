package com.jbqneto.dev.adventurebooks.domain.exception;

public class InvalidBookException extends BusinessViolationException {
    public InvalidBookException(String message) {
        super(message);
    }
}
