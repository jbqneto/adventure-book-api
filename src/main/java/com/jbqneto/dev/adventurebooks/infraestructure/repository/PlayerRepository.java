package com.jbqneto.dev.adventurebooks.infraestructure.repository;

import com.jbqneto.dev.adventurebooks.domain.model.Player;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsername(@NotBlank @Size(max = 80) String username);
}
