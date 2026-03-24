package com.jbqneto.dev.adventurebooks.domain.exception;

public class InvalidBookException extends RuntimeException {
    public InvalidBookException(String message) {
        super(message);
    }
}
