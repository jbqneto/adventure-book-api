package com.jbqneto.dev.adventurebooks.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "section_option")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String description;

    @ManyToOne
    @JoinColumn(name = "next_section_id")
    private Section nextSection;

    @ManyToOne(optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @OneToOne
    @JoinColumn(name = "consequence_id", unique = true)
    private Consequence consequence;
}
