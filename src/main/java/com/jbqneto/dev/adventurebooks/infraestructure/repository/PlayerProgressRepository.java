package com.jbqneto.dev.adventurebooks.infraestructure.repository;

import com.jbqneto.dev.adventurebooks.domain.enumType.ProgressStatus;
import com.jbqneto.dev.adventurebooks.domain.model.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Long> {

    List<PlayerProgress> findByPlayerIdAndStatus(Long playerId, ProgressStatus status);

}
