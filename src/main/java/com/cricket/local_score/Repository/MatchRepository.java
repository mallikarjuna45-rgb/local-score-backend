package com.cricket.local_score.Repository;

import com.cricket.local_score.Entity.MatchEntity;
import com.cricket.local_score.Entity.TeamEntity;
import com.cricket.local_score.Common.Enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {

    // ✅ Find by name
    List<MatchEntity> findByNameContainingIgnoreCase(String name);
    List<MatchEntity> findByTeamEntity1_NameIgnoreCaseOrTeamEntity2_NameIgnoreCase(String teamName1, String teamName2);
    List<MatchEntity> findByTournamentEntity_NameContainingIgnoreCase(String tournamentName);
    List<MatchEntity> findByMatchDateAfter(Date date);

    // ✅ Find by team name (team1 or team2)
    @Query("SELECT m FROM MatchEntity m WHERE LOWER(m.teamEntity1.name) LIKE LOWER(CONCAT('%', :teamName, '%')) OR LOWER(m.teamEntity2.name) LIKE LOWER(CONCAT('%', :teamName, '%'))")
    List<MatchEntity> findByTeamName(@Param("teamName") String teamName);

    // ✅ Find by tournament name
    @Query("SELECT m FROM MatchEntity m WHERE LOWER(m.tournamentEntity.name) LIKE LOWER(CONCAT('%', :tournamentName, '%'))")
    List<MatchEntity> findByTournamentName(@Param("tournamentName") String tournamentName);

    // ✅ Find by address (location)
    @Query("SELECT m FROM MatchEntity m WHERE LOWER(m.addressEntity.address2) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<MatchEntity> findByLocation(@Param("location") String location);

    // ✅ Find by status
    List<MatchEntity> findByMatchStatus(MatchStatus matchStatus);

    // ✅ Find all upcoming matches
    @Query("SELECT m FROM MatchEntity m WHERE m.matchDate < CURRENT_DATE AND m.matchStatus = 'SCHEDULED'")
    List<MatchEntity> findUpcomingMatches();
    List<MatchEntity> findByAddressEntity_Address2ContainingIgnoreCase(String location);

    List<MatchEntity>findByOwner_UserId(Integer userId);
}
