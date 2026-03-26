package com.jbqneto.dev.adventurebooks.domain.exception;

public class NoOptionException extends BusinessViolationException {
    public NoOptionException(String message) {
        super(message);
    }

    public NoOptionException() {
        this("A non-ending section must have options");
    }
}
