package com.jbqneto.dev.adventurebooks.domain.exception;

public class NoBeginningException extends RuntimeException {

    NoBeginningException() {
        super("Book should have 1 beggining");
    }
}
