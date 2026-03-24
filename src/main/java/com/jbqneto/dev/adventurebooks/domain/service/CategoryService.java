package com.jbqneto.dev.adventurebooks.domain.service;

import com.jbqneto.dev.adventurebooks.api.dto.request.CreateCategoryRequestDto;
import com.jbqneto.dev.adventurebooks.api.dto.response.CategoryResponseDto;
import com.jbqneto.dev.adventurebooks.domain.exception.CategoryAlreadyExistsException;
import com.jbqneto.dev.adventurebooks.domain.exception.CategoryNotFoundException;
import com.jbqneto.dev.adventurebooks.domain.model.Category;
import com.jbqneto.dev.adventurebooks.infraestructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> new CategoryResponseDto(c.getId(), c.getName()))
                .toList();
    }

    public CategoryResponseDto create(CreateCategoryRequestDto request) {
        var categoryName = request.name().toUpperCase();
        var existing = categoryRepository.findByName(categoryName);

        if (existing.isPresent()) {
            throw new CategoryAlreadyExistsException();
        }

        var category = new Category();
        category.setName(categoryName);

        var saved = categoryRepository.save(category);

        return new CategoryResponseDto(saved.getId(), saved.getName());
    }

    public void delete(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found: " + id));

        categoryRepository.delete(category);
    }
}
