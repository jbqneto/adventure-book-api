package com.jbqneto.dev.adventurebooks.infraestructure.repository;

import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;
import com.jbqneto.dev.adventurebooks.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        SELECT DISTINCT b FROM Book b
        LEFT JOIN b.categories c
        WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
        AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))
        AND (:difficulty IS NULL OR b.difficulty = :difficulty)
        AND (:category IS NULL OR LOWER(c.name) = LOWER(:category))
    """)
    List<Book> findBooksWithFilters(
            @Param("title") String title,
            @Param("author") String author,
            @Param("category") String category,
            @Param("difficulty") DifficultyLevel difficulty
    );

    Optional<Book> findBookByTitleAndAuthor(String title, String author);
}
