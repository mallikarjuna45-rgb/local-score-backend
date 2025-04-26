package com.cricket.local_score.Service.matchEntity;

import com.cricket.local_score.Entity.MatchEntity;
import com.cricket.local_score.dto.matchDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.request.createMatchRequest;

import java.util.List;

public interface ImatchService {
    // ✅ Create a match
    matchDto createMatch(createMatchRequest request);

    // ✅ Delete a match
    void deleteMatch(Integer matchId);

    // ✅ Update match details (generic update)
    MatchEntity updateMatch(Integer matchId, MatchEntity updatedDetails);

    // ✅ Get match by ID
    MatchEntity getMatchById(Integer matchId);

    // ✅ Find matches by name
    List<matchDto> findMatchesByName(String name);

    // ✅ Find matches by location (address)
    List<matchDto> findMatchesByLocation(String location);

    // ✅ Find matches by team name
    List<matchDto> findMatchesByTeamName(String teamName);

    // ✅ Find matches by tournament name
    List<matchDto> findMatchesByTournamentName(String tournamentName);

    // ✅ Find all matches
    List<MatchEntity> findAllMatches();

    // ✅ Find all ongoing matches
    List<matchDto> findAllOngoingMatches();

    // ✅ Find all scheduled matches
    List<matchDto> findAllScheduledMatches();

    // ✅ Find all abandoned matches
    List<matchDto> findAllAbandonedMatches();

    // ✅ Find all upcoming matches (scheduled after current date)
    List<matchDto> findAllUpcomingMatches();

    // ✅ Set target overs and target runs together
    matchDto setTargetDetails(Integer matchId, Integer targetRuns, Integer targetOvers);
    List<teamDto> getTeamsByMatchId(Integer matchId);
    void setTeam1Score(Integer matchId, Integer score);
    void setTeam2Score(Integer matchId, Integer score);
    // ✅ Set toss winner, match status (ongoing), and toss decision
//    matchDto setTossWinnerAndStatus(Integer matchId, Integer tossWinnerTeamId, String tossDecision); // tossDecision = "BAT" / "BOWL"

    List<matchDto> findMatchesByUserId(Integer userId);
}
