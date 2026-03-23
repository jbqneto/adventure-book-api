package com.jbqneto.dev.adventurebooks.domain.exception;

public class InvalidBookException extends RuntimeException {

    InvalidBookException(String message) {
        super(message);
    }
}
