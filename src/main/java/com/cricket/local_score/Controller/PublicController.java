package com.cricket.local_score.Controller;

import com.cricket.local_score.Entity.PlayerEntity;
import com.cricket.local_score.Service.playerEntity.IplayerService;
import com.cricket.local_score.Service.teamEntity.IteamService;
import com.cricket.local_score.dto.playerDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api")
public class PublicController {

    @Autowired
    private IplayerService playerService;

    @Autowired
    private IteamService teamService;

    @GetMapping("/get-all-players")
    public ResponseEntity<ApiResponse> getAllPlayers() {
        try {
            List<PlayerEntity> players = playerService.getAllPlayers();
            List<playerDto> playersDto = new ArrayList<>();
            for (PlayerEntity player : players) {
                playersDto.add(convertToDto(player));
            }
            return ResponseEntity.ok(new ApiResponse("All players fetched", playersDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching players: " + e.getMessage(), null));
        }
    }

    public playerDto convertToDto(PlayerEntity player) {
        playerDto dto = new playerDto();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setRuns(player.getRuns());
        dto.setBoundaries(player.getBoundaries());
        dto.setBallsFaced(player.getBallsFaced());
        dto.setBallsBowled(player.getBallsBowled());
        dto.setWickets(player.getWickets());
        dto.setMatches(player.getMatches());
        dto.setBattingStrikeRate(player.getBattingStrikeRate());
        dto.setBowlingStrikeRate(player.getBowlingStrikeRate());
        dto.setCatches(player.getCatches());
        dto.setTeamEntities(player.getTeamEntities());
        return dto;
    }

    @GetMapping("get-all-teams")
    public ResponseEntity<ApiResponse> getAllTeams() {
        try {
            List<teamDto> teams = teamService.getAllTeams();
            return ResponseEntity.ok(new ApiResponse("All teams fetched", teams));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching teams: " + e.getMessage(), null));
        }
    }
}
