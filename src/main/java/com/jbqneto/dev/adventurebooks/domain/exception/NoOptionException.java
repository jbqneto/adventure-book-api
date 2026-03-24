package com.jbqneto.dev.adventurebooks.domain.exception;

public class NoOptionException extends RuntimeException {
    public NoOptionException() {
        super("A non-ending section must have options");
    }
}
