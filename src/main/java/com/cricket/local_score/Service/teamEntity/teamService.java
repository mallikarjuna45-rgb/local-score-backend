package com.cricket.local_score.Service.teamEntity;

import com.cricket.local_score.Entity.MatchEntity;
import com.cricket.local_score.Entity.PlayerEntity;
import com.cricket.local_score.Entity.TeamEntity;
import com.cricket.local_score.Repository.MatchRepository;
import com.cricket.local_score.Repository.PlayerRepository;
import com.cricket.local_score.Repository.TeamRepository;
import com.cricket.local_score.dto.playerDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.request.createTeamRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class teamService implements IteamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ModelMapper modelMapper;

    // ✅ Create a team using createTeamRequest and return teamDto
    public teamDto createTeam(createTeamRequest request) {
        List<Integer> playerIds = request.getPlayerIds();

        if (playerIds == null || playerIds.size() != 11) {
            throw new RuntimeException("Team must contain exactly 11 unique players");
        }

        Set<PlayerEntity> players = new HashSet<>();
        for (Integer id : playerIds) {
            PlayerEntity player = playerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Player not found with ID: " + id));
            players.add(player);
        }

        if (players.size() != 11) {
            throw new RuntimeException("Players must be unique");
        }

        TeamEntity team = new TeamEntity();
        team.setName(request.getTeamName());
        team.setSize(11);
        team.setMatches(0);
        team.setWins(0);
        team.setLoss(0);
        team.setDraw(0);
        team.setPlayerEntities(players);

        TeamEntity savedTeam = teamRepository.save(team);

        // Update players to include the team
        for (PlayerEntity player : players) {
            player.getTeamEntities().add(savedTeam);
            playerRepository.save(player);
        }

        return modelMapper.map(savedTeam, teamDto.class);
    }

    // ✅ Get all team details
    public List<teamDto> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(team -> modelMapper.map(team, teamDto.class))
                .collect(Collectors.toList());
    }

    // ✅ Get a team by ID
    public teamDto getTeamById(Integer teamId) {
        TeamEntity team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return modelMapper.map(team, teamDto.class);
    }

    // ✅ Search team by name
    public List<teamDto> searchTeamByName(String name) {
        return teamRepository.findAll()
                .stream()
                .filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()))
                .map(t -> modelMapper.map(t, teamDto.class))
                .collect(Collectors.toList());
    }

    // ✅ Get players of a team by team ID
    public List<playerDto> getTeamPlayerByTeamId(Integer teamId) {
        TeamEntity team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        return team.getPlayerEntities()
                .stream()
                .map(p -> modelMapper.map(p, playerDto.class))
                .collect(Collectors.toList());
    }

    // ✅ Delete a team
    public void deleteTeam(Integer teamId) {
        TeamEntity team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        for (PlayerEntity player : team.getPlayerEntities()) {
            player.getTeamEntities().remove(team);
            playerRepository.save(player);
        }

        teamRepository.delete(team);
    }

    // ✅ Get matches played by a team
//    public List<MatchEntity> getMatchesPlayed(Integer teamId) {
//        TeamEntity team = teamRepository.findById(teamId)
//                .orElseThrow(() -> new RuntimeException("Team not found"));
//        return matchRepository.findByTeamsContaining(team);
//    }

    // ✅ Update matches played
    public TeamEntity updateMatchesPlayed(Integer teamId, Integer matches) {
        TeamEntity team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        team.setMatches(matches);
        return teamRepository.save(team);
    }
}