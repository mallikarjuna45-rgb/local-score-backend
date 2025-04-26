package com.cricket.local_score.Controller;

import com.cricket.local_score.Entity.MatchEntity;
import com.cricket.local_score.dto.matchDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.request.createMatchRequest;
import com.cricket.local_score.Service.matchEntity.ImatchService;
import com.cricket.local_score.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private ImatchService matchService;

    // Create a new match
    @PostMapping
    public ResponseEntity<ApiResponse> createMatch(@RequestBody createMatchRequest request) {
        try {
            matchDto createdMatch = matchService.createMatch(request);
            return new ResponseEntity<>(new ApiResponse("Match created successfully", createdMatch), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while creating match: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity<ApiResponse> deleteMatch(@PathVariable Integer matchId) {
        try {
            matchService.deleteMatch(matchId);
            return new ResponseEntity<>(new ApiResponse("Match deleted successfully", null), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Error while deleting match: " + e.getMessage(), null));
        }
    }



    @GetMapping("/{matchId}")
    public ResponseEntity<ApiResponse> getMatchById(@PathVariable Integer matchId) {
        try {
            MatchEntity match = matchService.getMatchById(matchId);
            return new ResponseEntity<>(new ApiResponse("Match fetched successfully", match), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Match not found: " + e.getMessage(), null));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> findMatchesByName(@PathVariable String name) {
        try {
            List<matchDto> matches = matchService.findMatchesByName(name);
            return new ResponseEntity<>(new ApiResponse("Matches by name fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching matches by name: " + e.getMessage(), null));
        }
    }


    @GetMapping("/location/{location}")
    public ResponseEntity<ApiResponse> findMatchesByLocation(@PathVariable String location) {
        try {
            List<matchDto> matches = matchService.findMatchesByLocation(location);
            return new ResponseEntity<>(new ApiResponse("Matches by location fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching matches by location: " + e.getMessage(), null));
        }
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<ApiResponse> findMatchesByTeamName(@PathVariable String teamName) {
        try {
            List<matchDto> matches = matchService.findMatchesByTeamName(teamName);
            return new ResponseEntity<>(new ApiResponse("Matches by team name fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching matches by team name: " + e.getMessage(), null));
        }
    }

    @GetMapping("/tournament/{tournamentName}")
    public ResponseEntity<ApiResponse> findMatchesByTournamentName(@PathVariable String tournamentName) {
        try {
            List<matchDto> matches = matchService.findMatchesByTournamentName(tournamentName);
            return new ResponseEntity<>(new ApiResponse("Matches by tournament name fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching matches by tournament: " + e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllMatches() {
        try {
            List<MatchEntity> matches = matchService.findAllMatches();
            return new ResponseEntity<>(new ApiResponse("All matches fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching all matches: " + e.getMessage(), null));
        }
    }

    @GetMapping("/ongoing")
    public ResponseEntity<ApiResponse> findAllOngoingMatches() {
        try {
            List<matchDto> matches = matchService.findAllOngoingMatches();
            return new ResponseEntity<>(new ApiResponse("Ongoing matches fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching ongoing matches: " + e.getMessage(), null));
        }
    }

    @GetMapping("/scheduled")
    public ResponseEntity<ApiResponse> findAllScheduledMatches() {
        try {
            List<matchDto> matches = matchService.findAllScheduledMatches();
            return new ResponseEntity<>(new ApiResponse("Scheduled matches fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching scheduled matches: " + e.getMessage(), null));
        }
    }

    @GetMapping("/abandoned")
    public ResponseEntity<ApiResponse> findAllAbandonedMatches() {
        try {
            List<matchDto> matches = matchService.findAllAbandonedMatches();
            return new ResponseEntity<>(new ApiResponse("Abandoned matches fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching abandoned matches: " + e.getMessage(), null));
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<ApiResponse> findAllUpcomingMatches() {
        try {
            List<matchDto> matches = matchService.findAllUpcomingMatches();
            return new ResponseEntity<>(new ApiResponse("Upcoming matches fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching upcoming matches: " + e.getMessage(), null));
        }
    }
//        @PutMapping("/{matchId}/team1-score")
//    public ResponseEntity<String> setTeam1Score(@PathVariable Integer matchId, @RequestParam Integer score) {
//        try {
//            matchService.setTeam1Score(matchId, score);
//            return ResponseEntity.ok("Team 1 score updated and winner determined.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error setting Team 1 score: " + e.getMessage());
//        }
//    }

    // Set Team2 Score
//    @PutMapping("/{matchId}/team2-score")
//    public ResponseEntity<String> setTeam2Score(@PathVariable Integer matchId, @RequestParam Integer score) {
//        try {
//            matchService.setTeam2Score(matchId, score);
//            return ResponseEntity.ok("Team 2 score updated and winner determined.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error setting Team 2 score: " + e.getMessage());
//        }
//    }

    // Get the Winner of the Match
//    @GetMapping("/{matchId}/winner")
//    public ResponseEntity<String> getMatchWinner(@PathVariable Integer matchId) {
//        try {
//            MatchEntity match = matchService.getMatchById(matchId);
//            if (match.getMatchWinner() != null) {
//                return ResponseEntity.ok("Match Winner: " + match.getMatchWinner().getName());
//            } else {
//                return ResponseEntity.ok("The match is a draw or still ongoing.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error fetching match winner: " + e.getMessage());
//        }
//    }
 //   }

//    @PutMapping("/{matchId}/target")
//    public ResponseEntity<ApiResponse> setTargetDetails(@PathVariable Integer matchId, @RequestParam Integer targetRuns, @RequestParam Integer targetOvers) {
//        try {
//            matchDto updatedMatch = matchService.setTargetDetails(matchId, targetRuns, targetOvers);
//            return new ResponseEntity<>(new ApiResponse("Target details updated", updatedMatch), HttpStatus.OK);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse("Error while setting target details: " + e.getMessage(), null));
//        }
//    }

//    @PutMapping("/{matchId}/toss")
//    public ResponseEntity<ApiResponse> setTossWinnerAndStatus(@PathVariable Integer matchId, @RequestParam Integer tossWinnerTeamId, @RequestParam String tossDecision) {
//        try {
//            matchDto updatedMatch = matchService.setTossWinnerAndStatus(matchId, tossWinnerTeamId, tossDecision);
//            return new ResponseEntity<>(new ApiResponse("Toss winner and status updated", updatedMatch), HttpStatus.OK);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse("Error while setting toss winner and status: " + e.getMessage(), null));
//        }
//    }

    @GetMapping("/matches/{matchId}/teams")
    public ResponseEntity<List<teamDto>> getTeamsByMatchId(@PathVariable Integer matchId) {
        try {
            List<teamDto> teams = matchService.getTeamsByMatchId(matchId);
            return ResponseEntity.ok(teams);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }
    @PutMapping("/{matchId}/team1-score")
    public ResponseEntity<String> setTeam1Score(@PathVariable Integer matchId, @RequestParam Integer score) {
        try {
            matchService.setTeam1Score(matchId, score);
            return ResponseEntity.ok("Team 1 score updated and winner determined.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error setting Team 1 score: " + e.getMessage());
        }
    }

    // Set Team2 Score
    @PutMapping("/{matchId}/team2-score")
    public ResponseEntity<String> setTeam2Score(@PathVariable Integer matchId, @RequestParam Integer score) {
        try {
            matchService.setTeam2Score(matchId, score);
            return ResponseEntity.ok("Team 2 score updated and winner determined.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error setting Team 2 score: " + e.getMessage());
        }
    }

    // Get the Winner of the Match
//    @GetMapping("/{matchId}/winner")
//    public ResponseEntity<String> getMatchWinner(@PathVariable Integer matchId) {
//        try {
//            matchDto match = matchService.getMatchById(matchId);
//            if (match.getMatchWinnerName()!= null) {
//                return ResponseEntity.ok("Match Winner: " + match.getMatchWinnerName());
//            } else {
//                return ResponseEntity.ok("The match is a draw or still ongoing.");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error fetching match winner: " + e.getMessage());
//        }
//    }




    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> findMatchesByName(@PathVariable Integer userId) {
        try {
            List<matchDto> matches = matchService.findMatchesByUserId(userId);
            return new ResponseEntity<>(new ApiResponse("Matches by user fetched", matches), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while fetching matches by user: " + e.getMessage(), null));
        }
    }

}
