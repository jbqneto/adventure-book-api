package com.jbqneto.dev.adventurebooks.domain.exception;

public class SectionNotFoundException extends NotFoundException {

    public SectionNotFoundException(String message) {
        super(message);
    }

    public static SectionNotFoundException BeginningSectionNotFound() {
        return new SectionNotFoundException("Beginning section not found!");
    }
}
