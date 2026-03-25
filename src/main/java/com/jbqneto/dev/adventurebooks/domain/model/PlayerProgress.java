package com.jbqneto.dev.adventurebooks.domain.model;

import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * One player can be playing multiple games at the same time
 * and be on different sections of different books.
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(
        name = "player_progress",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_player_book_progress", columnNames = {"player_id", "book_id"})
        }
)
public class PlayerProgress {

    public static final int INITIAL_HEALTH = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "current_section_id", nullable = false)
    private Section currentSection;

    @Column(nullable = false)
    private int health = INITIAL_HEALTH;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgressStatus status = ProgressStatus.IN_PROGRESS;
}
