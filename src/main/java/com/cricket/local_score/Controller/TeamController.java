package com.cricket.local_score.Controller;

import com.cricket.local_score.Service.teamEntity.IteamService;
import com.cricket.local_score.dto.playerDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.request.createTeamRequest;
import com.cricket.local_score.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private IteamService teamService;

    // ✅ Create a team
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTeam(@RequestBody createTeamRequest request) {
        System.out.println("Received team creation request: " + request);
        try {
            teamDto createdTeam = teamService.createTeam(request);
            return ResponseEntity.ok(new ApiResponse("Team created successfully", createdTeam));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while creating team: " + e.getMessage(), null));
        }
    }

    // ✅ Get all teams
    @GetMapping
    public ResponseEntity<ApiResponse> getAllTeams() {
        try {
            List<teamDto> teams = teamService.getAllTeams();
            return ResponseEntity.ok(new ApiResponse("All teams fetched", teams));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching teams: " + e.getMessage(), null));
        }
    }

    // ✅ Get a team by ID
    @GetMapping("/{teamId}")
    public ResponseEntity<ApiResponse> getTeamById(@PathVariable Integer teamId) {
        try {
            teamDto team = teamService.getTeamById(teamId);
            return ResponseEntity.ok(new ApiResponse("Team found", team));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Team not found: " + e.getMessage(), null));
        }
    }

    // ✅ Search teams by name
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchTeams(@RequestParam String name) {
        try {
            List<teamDto> teams = teamService.searchTeamByName(name);
            return ResponseEntity.ok(new ApiResponse("Teams matching search found", teams));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while searching teams: " + e.getMessage(), null));
        }
    }

    // ✅ Get players in a team
    @GetMapping("/{teamId}/players")
    public ResponseEntity<ApiResponse> getPlayersByTeam(@PathVariable Integer teamId) {
        try {
            List<playerDto> players = teamService.getTeamPlayerByTeamId(teamId);
            return ResponseEntity.ok(new ApiResponse("Players in team", players));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Error while fetching players: " + e.getMessage(), null));
        }
    }

    // ✅ Delete a team
    @DeleteMapping("/{teamId}")
    public ResponseEntity<ApiResponse> deleteTeam(@PathVariable Integer teamId) {
        try {
            teamService.deleteTeam(teamId);
            return ResponseEntity.ok(new ApiResponse("Team deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Error while deleting team: " + e.getMessage(), null));
        }
    }


    // ✅ Get matches played by a team
//    @GetMapping("/{teamId}/matches")
//    public ResponseEntity<ApiResponse> getMatchesByTeam(@PathVariable Integer teamId) {
//        return ResponseEntity.ok(new ApiResponse("Matches fetched", teamService.getMatchesPlayed(teamId)));
//    }

    // ✅ Update match count
//    @PutMapping("/{teamId}/matches")
//    public ResponseEntity<ApiResponse> updateMatchCount(@PathVariable Integer teamId, @RequestParam Integer count) {
//        return ResponseEntity.ok(new ApiResponse("Match count updated", teamService.updateMatchesPlayed(teamId, count)));
//    }
}
