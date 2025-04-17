package com.cricket.local_score.request;

import com.cricket.local_score.Common.Enums.MatchStatus;
import com.cricket.local_score.Common.Enums.TossDecision;
import lombok.Data;

import java.util.Date;

@Data
public class createMatchRequest {
    private String name;
    private Date matchDate;
    private Integer totalOvers;
    private Integer targetRuns;
    private Integer targetOvers;
    private Integer superOver;

    private TossDecision tossDecision;
    private MatchStatus matchStatus;

    private Integer tournamentId;
    private Integer playerOfMatchId;
    private Integer tossWinnerId;
    private Integer matchWinnerId;
    private Integer team1Id;
    private Integer team2Id;
    private Integer ownerId;
    private Integer addressId;
}
