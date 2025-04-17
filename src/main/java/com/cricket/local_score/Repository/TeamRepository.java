package com.cricket.local_score.Repository;


import com.cricket.local_score.Entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity,Integer> {


    Optional<TeamEntity> findByName(String name);
}
