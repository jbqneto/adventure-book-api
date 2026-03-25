package com.jbqneto.dev.adventurebooks.api.dto.response;

import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;

public record SectionSummaryDto(
        Long id,
        Long reference,
        SectionType type
) {}