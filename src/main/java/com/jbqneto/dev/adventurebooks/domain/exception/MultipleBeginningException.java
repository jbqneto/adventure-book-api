package com.jbqneto.dev.adventurebooks.domain.exception;

public class MultipleBeginningException extends RuntimeException {
    public MultipleBeginningException() {
        super("Book should not have more then 1 beginning");
    }
}
