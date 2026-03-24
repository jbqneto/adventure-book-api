package com.jbqneto.dev.adventurebooks.domain.exception;

public class PlayerNotFoundException extends NotFoundException {
    public PlayerNotFoundException() {
        super("Player not found");
    }
}
