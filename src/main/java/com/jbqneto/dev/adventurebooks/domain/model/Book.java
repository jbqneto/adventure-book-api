package com.jbqneto.dev.adventurebooks.domain.model;

import com.jbqneto.dev.adventurebooks.domain.enumType.DifficultyLevel;
import com.jbqneto.dev.adventurebooks.domain.enumType.SectionType;
import com.jbqneto.dev.adventurebooks.domain.exception.SectionNotFoundException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"categories", "sections"})
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficulty;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Section> sections = new ArrayList<>();

    public Section getBeginningSection() {
        return this.sections.stream()
                .filter(sec -> sec.getType() == SectionType.BEGIN)
                .findFirst()
                .orElseThrow(SectionNotFoundException::BeginningSectionNotFound);
    }

}
