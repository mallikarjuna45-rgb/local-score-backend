package com.cricket.local_score.Service.teamEntity;

import com.cricket.local_score.Entity.MatchEntity;
import com.cricket.local_score.Entity.TeamEntity;
import com.cricket.local_score.dto.playerDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.request.createTeamRequest;

import java.util.List;

public interface IteamService {
    // ✅ Create a team (must have exactly 11 unique players)
    teamDto createTeam(createTeamRequest team);

    // ✅ Get all team details
    List<teamDto> getAllTeams();

    // ✅ Get a team by ID
    teamDto getTeamById(Integer teamId);

    // ✅ Search team by name (case-insensitive, partial match)
    List<teamDto> searchTeamByName(String name);
    List<playerDto> getTeamPlayerByTeamId(Integer teamId);
    // ✅ Delete a team by ID
    void deleteTeam(Integer teamId);

    // ✅ Get total matches played by a team
    //List<MatchEntity> getMatchesPlayed(Integer teamId);

  //  TeamEntity updateMatchesPlayed(Integer teamId, Integer matches);
}
