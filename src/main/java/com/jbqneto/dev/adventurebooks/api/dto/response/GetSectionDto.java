package com.jbqneto.dev.adventurebooks.api.dto.response;

import java.util.List;

public record GetSectionDto (
    Long id,
    String text,
    List<GetOptionDto> options
) {}
