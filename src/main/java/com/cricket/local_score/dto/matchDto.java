package com.cricket.local_score.dto;

import com.cricket.local_score.Common.Enums.MatchStatus;
import com.cricket.local_score.Common.Enums.TossDecision;
import lombok.Data;

import java.util.Date;

@Data
public class matchDto {
    private Integer id;
    private String name;
    private Date matchDate;
    private Integer totalOvers;
//    private Integer targetRuns;
//    private Integer targetOvers;
    private TossDecision tossDecision;
    private MatchStatus matchStatus;

    private Integer tournamentId;
    private String tournamentName;

//    private Integer playerOfMatchId;
//    private String playerOfMatchName;

    private Integer tossWinnerId;
    private String tossWinnerName;

    private Integer matchWinnerId;
    private String matchWinnerName;

    private Integer team1Id;
    private String team1Name;

    private Integer team2Id;
    private String team2Name;

    private Integer ownerId;
    private String ownerUsername;

    private Integer addressId;
    private String address2;
    private Integer pincode;
}
