package com.jbqneto.dev.adventurebooks.api.dto.response;

public record GetOptionDto(
        Long id,
        String description,
        Long gotoId,
        GetConsequenceDto consequence
) {
    public GetOptionDto(Long id, String description, Long gotoId) {
        this(id, description, gotoId, null);
    }
}
