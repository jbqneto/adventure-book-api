package com.jbqneto.dev.adventurebooks.domain.exception;

public class NoBeginningException extends BusinessViolationException {

    public NoBeginningException() {
        super("Book should have 1 beggining");
    }
}
