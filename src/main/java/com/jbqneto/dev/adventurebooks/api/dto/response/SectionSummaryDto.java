package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;

import java.util.List;

public record SectionSummaryDto(
        Long id,
        Long reference,
        SectionType type,
        List<GetOptionDto> options
) {}