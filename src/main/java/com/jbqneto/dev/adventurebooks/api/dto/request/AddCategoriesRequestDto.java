package com.jbqneto.dev.adventurebooks.api.dto.request;

import java.util.Set;

public record AddCategoriesRequestDto(
        Set<Long> categories
) {
}
