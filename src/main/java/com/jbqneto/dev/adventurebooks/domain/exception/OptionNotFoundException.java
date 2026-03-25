package com.jbqneto.dev.adventurebooks.domain.exception;

public class OptionNotFoundException extends NotFoundException {

    public OptionNotFoundException(String message) {
        super(message);
    }

    public OptionNotFoundException() {
        this("Option not found");
    }
}
