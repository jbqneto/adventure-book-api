package com.jbqneto.dev.adventurebooks.domain.exception;

public class NonEndingException extends RuntimeException {
    public NonEndingException() {
        super("Book should have at least 1 ending");
    }
}
