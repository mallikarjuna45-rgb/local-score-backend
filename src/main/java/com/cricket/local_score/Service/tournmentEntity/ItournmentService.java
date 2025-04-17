package com.cricket.local_score.Service.tournmentEntity;

import com.cricket.local_score.Entity.TeamEntity;
import com.cricket.local_score.dto.playerDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.dto.tournamentDto;
import com.cricket.local_score.request.CreateTournamentRequest;

import java.util.List;

public interface ItournmentService {
    // ✅ Get all tournaments
    List<tournamentDto> getAllTournaments();

    // ✅ Get ongoing tournaments
    List<tournamentDto> getOngoingTournaments();

    // ✅ Get scheduled tournaments
    List<tournamentDto> getScheduledTournaments();

    // ✅ Get past tournaments
    List<tournamentDto> getPastTournaments();

    // ✅ Get tournaments by location
    List<tournamentDto> getTournamentsByLocation(String location);

    // ✅ Get tournament by ID
    tournamentDto getTournamentById(Integer tournamentId);

    // ✅ Create a new tournament
    tournamentDto createTournament(CreateTournamentRequest request);

    // ✅ Update a tournament
    tournamentDto updateTournament(Integer tournamentId, CreateTournamentRequest request);

    // ✅ Delete a tournament
    void deleteTournament(Integer tournamentId);

    // ✅ Get all teams in a tournament
    List<teamDto> getTeamsInTournament(Integer tournamentId);
    // Add these methods
    void addTeamToTournament(Integer tournamentId, String team);
    void removeTeamFromTournament(Integer tournamentId, Integer teamId);

    // ✅ Get all players in a tournament
    List<playerDto> getPlayersInTournament(Integer tournamentId);
}

