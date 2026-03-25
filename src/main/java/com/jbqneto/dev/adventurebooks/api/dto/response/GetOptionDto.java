package com.jbqneto.dev.adventurebooks.api.dto.response;

public record GetOptionDto(
        Long id,
        String text,
        Long nextSectionReference
) {
}
