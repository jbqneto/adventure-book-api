package com.jbqneto.dev.adventurebooks.domain.exception;

public class AdventureNotFoundException extends NotFoundException {
    public AdventureNotFoundException() {
      super("Adventure not found");
    }
}
