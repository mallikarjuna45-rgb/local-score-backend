package com.cricket.local_score.Repository;

import com.cricket.local_score.Entity.MatchEntity;
import com.cricket.local_score.Entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {
    List<PlayerEntity> findByName(String name);
    // List<PlayerEntity> findByNameContainingIgnoreCase(String name);
}
