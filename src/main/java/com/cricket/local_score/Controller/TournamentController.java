package com.cricket.local_score.Controller;
import com.cricket.local_score.Service.tournmentEntity.tournmentService;
import com.cricket.local_score.dto.tournamentDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.request.CreateTournamentRequest;
import com.cricket.local_score.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final tournmentService tournamentService;

    // Create Tournament
    @PostMapping
    public ResponseEntity<ApiResponse> createTournament(@RequestBody CreateTournamentRequest request) {
        try {
            tournamentDto createdTournament = tournamentService.createTournament(request);
            return new ResponseEntity<>(new ApiResponse("Tournament created successfully", createdTournament), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while creating tournament: " + e.getMessage(), null));
        }
    }

    // Get All Tournaments
    @GetMapping
    public ResponseEntity<ApiResponse> getAllTournaments() {
        try {
            List<tournamentDto> tournaments = tournamentService.getAllTournaments();
            return new ResponseEntity<>(new ApiResponse("Tournaments fetched successfully", tournaments), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching tournaments: " + e.getMessage(), null));
        }
    }

    // Get Tournament By ID
    @GetMapping("/{tournamentId}")
    public ResponseEntity<ApiResponse> getTournamentById(@PathVariable Integer tournamentId) {
        try {
            tournamentDto tournament = tournamentService.getTournamentById(tournamentId);
            return new ResponseEntity<>(new ApiResponse("Tournament fetched successfully", tournament), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching tournament: " + e.getMessage(), null));
        }
    }

    // Get Ongoing Tournaments
    @GetMapping("/ongoing")
    public ResponseEntity<ApiResponse> getOngoingTournaments() {
        try {
            List<tournamentDto> tournaments = tournamentService.getOngoingTournaments();
            return new ResponseEntity<>(new ApiResponse("Ongoing tournaments fetched successfully", tournaments), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching ongoing tournaments: " + e.getMessage(), null));
        }
    }

    // Get Scheduled Tournaments
    @GetMapping("/scheduled")
    public ResponseEntity<ApiResponse> getScheduledTournaments() {
        try {
            List<tournamentDto> tournaments = tournamentService.getScheduledTournaments();
            return new ResponseEntity<>(new ApiResponse("Scheduled tournaments fetched successfully", tournaments), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching scheduled tournaments: " + e.getMessage(), null));
        }
    }

    // Get Past Tournaments
    @GetMapping("/past")
    public ResponseEntity<ApiResponse> getPastTournaments() {
        try {
            List<tournamentDto> tournaments = tournamentService.getPastTournaments();
            return new ResponseEntity<>(new ApiResponse("Past tournaments fetched successfully", tournaments), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching past tournaments: " + e.getMessage(), null));
        }
    }

    // Get Tournaments by Location
    @GetMapping("/location/{location}")
    public ResponseEntity<ApiResponse> getTournamentsByLocation(@PathVariable String location) {
        try {
            List<tournamentDto> tournaments = tournamentService.getTournamentsByLocation(location);
            return new ResponseEntity<>(new ApiResponse("Tournaments fetched by location successfully", tournaments), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching tournaments by location: " + e.getMessage(), null));
        }
    }

    // Update Tournament
    @PutMapping("/{tournamentId}")
    public ResponseEntity<ApiResponse> updateTournament(@PathVariable Integer tournamentId, @RequestBody CreateTournamentRequest request) {
        try {
            tournamentDto updatedTournament = tournamentService.updateTournament(tournamentId, request);
            return new ResponseEntity<>(new ApiResponse("Tournament updated successfully", updatedTournament), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while updating tournament: " + e.getMessage(), null));
        }
    }

    // Delete Tournament
    @DeleteMapping("/{tournamentId}")
    public ResponseEntity<ApiResponse> deleteTournament(@PathVariable Integer tournamentId) {
        try {
            tournamentService.deleteTournament(tournamentId);
            return new ResponseEntity<>(new ApiResponse("Tournament deleted successfully", null), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while deleting tournament: " + e.getMessage(), null));
        }
    }

    // Add Team to Tournament
    @PostMapping("/{tournamentId}/teams/{teamId}")
    public ResponseEntity<ApiResponse> addTeamToTournament(@PathVariable Integer tournamentId, @PathVariable String teamId) {
        try {
            tournamentService.addTeamToTournament(tournamentId, teamId);
            return new ResponseEntity<>(new ApiResponse("Team added to tournament successfully", null), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while adding team to tournament: " + e.getMessage(), null));
        }
    }

    // Remove Team from Tournament
    @DeleteMapping("/{tournamentId}/teams/{teamId}")
    public ResponseEntity<ApiResponse> removeTeamFromTournament(@PathVariable Integer tournamentId, @PathVariable Integer teamId) {
        try {
            tournamentService.removeTeamFromTournament(tournamentId, teamId);
            return new ResponseEntity<>(new ApiResponse("Team removed from tournament successfully", null), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while removing team from tournament: " + e.getMessage(), null));
        }
    }
}
