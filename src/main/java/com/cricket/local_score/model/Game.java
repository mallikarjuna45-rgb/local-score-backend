package com.cricket.local_score.model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Date matchDate;
    private Integer totalOvers;
    private Integer targetRuns;
    private Integer targetOvers;
    private Integer superOver;
    private String tossDecision; // Example: "BAT", "BOWL"
    private String matchStatus; // Example: "SCHEDULED", "ONGOING", "COMPLETED", "ABANDONED"

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament; // Match may or may not belong to a tournament

    @ManyToOne
    @JoinColumn(name = "player_of_match")
    private Player playerOfMatch; // ✅ Player of the match added

    @ManyToOne
    @JoinColumn(name = "toss_winner")
    private Team tossWinner; // Toss winner team

    @ManyToOne
    @JoinColumn(name = "match_winner")
    private Team matchWinner; // Match winner team

    @ManyToOne
    @JoinColumn(name = "team1")
    private Team team1; // First Team

    @ManyToOne
    @JoinColumn(name = "team2")
    private Team team2; // Second Team

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner; // User who created the match (if needed)
    
    @ManyToOne
    @JoinColumn(name="address_id")
    private Address adress;
}
