package com.cricket.local_score.Controller;

import com.cricket.local_score.Entity.PlayerEntity;
import com.cricket.local_score.Entity.TeamEntity;
import com.cricket.local_score.Service.playerEntity.IplayerService;
import com.cricket.local_score.dto.playerDto;
import com.cricket.local_score.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private IplayerService playerService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponse> createPlayer(@RequestBody PlayerEntity player, @PathVariable Integer userId) {
        try {
            PlayerEntity saved = playerService.createPlayer(player, userId);
            return ResponseEntity.ok(new ApiResponse("Player created successfully", saved));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while creating player: " + e.getMessage(), null));
        }
    }


    @GetMapping("/search")
    public ResponseEntity<ApiResponse> getPlayersByName(@RequestParam String name) {
        try {
            List<PlayerEntity> players = playerService.getPlayersByPlayerName(name);
            return ResponseEntity.ok(new ApiResponse("Players matched", players));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{playerId}/teams")
    public ResponseEntity<ApiResponse> getTeamsByPlayerId(@PathVariable Integer playerId) {
        try {
            Set<TeamEntity> teams = playerService.getTeamsByPlayerId(playerId);
            return ResponseEntity.ok(new ApiResponse("Teams retrieved", teams));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{playerId}/runs")
    public ResponseEntity<ApiResponse> updateRuns(@PathVariable Integer playerId, @RequestParam Integer runs) {
        try {
            PlayerEntity updated = playerService.updateRuns(playerId, runs);
            return ResponseEntity.ok(new ApiResponse("Runs updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{playerId}/boundaries")
    public ResponseEntity<ApiResponse> updateBoundaries(@PathVariable Integer playerId, @RequestParam Integer boundaries) {
        try {
            PlayerEntity updated = playerService.updateBoundaries(playerId, boundaries);
            return ResponseEntity.ok(new ApiResponse("Boundaries updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{playerId}/balls-faced")
    public ResponseEntity<ApiResponse> updateBallsFaced(@PathVariable Integer playerId, @RequestParam Integer ballsFaced) {
        try {
            PlayerEntity updated = playerService.updateBallsFaced(playerId, ballsFaced);
            return ResponseEntity.ok(new ApiResponse("Balls faced updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{playerId}/balls-bowled")
    public ResponseEntity<ApiResponse> updateBallsBowled(@PathVariable Integer playerId, @RequestParam Integer ballsBowled) {
        try {
            PlayerEntity updated = playerService.updateBallsBowled(playerId, ballsBowled);
            return ResponseEntity.ok(new ApiResponse("Balls bowled updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{playerId}/wickets")
    public ResponseEntity<ApiResponse> updateWickets(@PathVariable Integer playerId, @RequestParam Integer wickets) {
        try {
            PlayerEntity updated = playerService.updateWickets(playerId, wickets);
            return ResponseEntity.ok(new ApiResponse("Wickets updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{playerId}/matches")
    public ResponseEntity<ApiResponse> updateMatches(@PathVariable Integer playerId, @RequestParam Integer matches) {
        try {
            PlayerEntity updated = playerService.updateMatches(playerId, matches);
            return ResponseEntity.ok(new ApiResponse("Matches updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{playerId}/catches")
    public ResponseEntity<ApiResponse> updateCatches(@PathVariable Integer playerId, @RequestParam Integer catches) {
        try {
            PlayerEntity updated = playerService.updateCatches(playerId, catches);
            return ResponseEntity.ok(new ApiResponse("Catches updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{playerId}/update")
    public ResponseEntity<ApiResponse> updateAllAttributes(@PathVariable Integer playerId, @RequestBody PlayerEntity updatedPlayer) {
        try {
            PlayerEntity updated = playerService.updateAllAttributes(playerId, updatedPlayer);
            return ResponseEntity.ok(new ApiResponse("Player updated", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
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
    @GetMapping("/players/{playerId}")
    public ResponseEntity<ApiResponse> getPlayerById(@PathVariable Integer playerId) {
        try {
            PlayerEntity player = playerService.getPlayerById(playerId);
            playerDto dto = convertToDto(player);
            return ResponseEntity.ok(new ApiResponse("Player fetched successfully", dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

}
