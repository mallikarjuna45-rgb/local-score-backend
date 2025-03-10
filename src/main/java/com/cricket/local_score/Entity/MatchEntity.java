package com.cricket.local_score.Entity;



import java.util.Date;

import com.cricket.local_score.Common.Enums.MatchStatus;
import com.cricket.local_score.Common.Enums.TossDecision;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Date matchDate;
    private Integer totalOvers;
    private Integer targetRuns;
    private Integer targetOvers;
    private Integer superOver;

    @Enumerated(EnumType.STRING)
    private TossDecision tossDecision; // Example: "BAT", "BOWL"

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus; // Example: "SCHEDULED", "ONGOING", "COMPLETED", "ABANDONED"

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private TournamentEntity tournamentEntity; // Match may or may not belong to a tournament

    @ManyToOne
    @JoinColumn(name = "player_of_match")
    private PlayerEntity playerEntityOfMatch; // ✅ Player of the match added

    @ManyToOne
    @JoinColumn(name = "toss_winner")
    private TeamEntity tossWinner; // Toss winner team

    @ManyToOne
    @JoinColumn(name = "match_winner")
    private TeamEntity matchWinner; // Match winner team

    @ManyToOne
    @JoinColumn(name = "team1")
    private TeamEntity teamEntity1; // First Team

    @ManyToOne
    @JoinColumn(name = "team2")
    private TeamEntity teamEntity2; // Second Team

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner; // User who created the match (if needed)
    
    @ManyToOne
    @JoinColumn(name="address_id")
    private AddressEntity addressEntity;
}
