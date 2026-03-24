package com.jbqneto.dev.adventurebooks.domain.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException() {
        super("Player not found");
    }
}
