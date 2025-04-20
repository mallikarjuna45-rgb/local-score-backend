package com.cricket.local_score.Controller;

import com.cricket.local_score.Service.matchEntity.ImatchService;
import com.cricket.local_score.dto.matchDto;
import com.cricket.local_score.request.createMatchRequest;
import com.cricket.local_score.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class PrivateController {

    @Autowired
    private ImatchService matchService;

    @PutMapping("/{matchId}")
    public ResponseEntity<ApiResponse> updateMatch(@PathVariable Integer matchId, @RequestBody createMatchRequest updatedDetails) {
        try {
            matchDto updatedMatch = matchService.updateMatch(matchId, updatedDetails);
            return new ResponseEntity<>(new ApiResponse("Match updated successfully", updatedMatch), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error while updating match: " + e.getMessage(), null));
        }
    }
}
