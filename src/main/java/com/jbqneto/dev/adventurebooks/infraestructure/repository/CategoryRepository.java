package com.jbqneto.dev.adventurebooks.infraestructure.repository;

import com.jbqneto.dev.adventurebooks.domain.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(@NotBlank @Size(max = 50) String name);
}
