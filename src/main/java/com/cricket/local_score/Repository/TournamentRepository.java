package com.cricket.local_score.Repository;

import com.cricket.local_score.Entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentEntity,Integer> {

     List<TournamentEntity> findByAddressEntity_Address2ContainingIgnoreCase(String location);
}
