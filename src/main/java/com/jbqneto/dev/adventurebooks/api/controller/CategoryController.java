package com.jbqneto.dev.adventurebooks.api.controller;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreateCategoryRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.CategoryResponseDto;
import com.jbqneto.dev.adventurebooks.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody CreateCategoryRequestDto request) {
        return categoryService.create(request);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }
}
