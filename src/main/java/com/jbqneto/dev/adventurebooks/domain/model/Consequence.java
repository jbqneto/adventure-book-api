package com.jbqneto.dev.adventurebooks.domain.model;

import com.jbqneto.dev.adventurebooks.domain.enumType.ConsequenceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "option_consequence")
public class Consequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ConsequenceType type;

    @Column(name = "effect_value", nullable = false)
    private int value;

    @Column(nullable = false)
    private String text;

    @OneToOne(mappedBy = "consequence")
    private Option option;
}
