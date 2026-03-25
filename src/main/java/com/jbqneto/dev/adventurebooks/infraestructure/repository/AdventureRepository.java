package com.jbqneto.dev.adventurebooks.infraestructure.repository;

import com.jbqneto.dev.adventurebooks.domain.model.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<PlayerProgress, Long> {
}
