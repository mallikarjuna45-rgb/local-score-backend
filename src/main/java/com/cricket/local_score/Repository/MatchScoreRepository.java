package com.cricket.local_score.Repository;

import com.cricket.local_score.Entity.MatchEntity;
import com.cricket.local_score.Entity.MatchScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchScoreRepository extends JpaRepository<MatchScore,Integer> {

}
