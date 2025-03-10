package com.cricket.local_score.Entity;
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
public class MatchScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private MatchEntity matchEntity;
    
    @ManyToOne
    @JoinColumn(name = "tournment_id", nullable = false)
    private TournamentEntity tournamentEntity;

    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "batting_team", nullable = false)
    private TeamEntity battingTeamEntity;
    
    @ManyToOne
    @JoinColumn(name = "bowling_team", nullable = false)
    private TeamEntity bowlingTeamEntity;
    
    private Integer inningsNo;
    private Integer ballNo;
    
    @ManyToOne
    @JoinColumn(name = "bowler", nullable = false)
    private PlayerEntity bowler;
    
    @ManyToOne
    @JoinColumn(name = "striker", nullable = false)
    private PlayerEntity striker;
    
    @ManyToOne
    @JoinColumn(name = "non_striker", nullable = false)
    private PlayerEntity nonStriker;
    
    private Integer runsScored;
    private Integer extras;
    private String typeOfExtras;
    private Integer score;
    private Integer scoreWicket;
    private Boolean wicketConfirmation;
    private String wicketType;
    private String fieldersInvolved;
    
    @ManyToOne
    @JoinColumn(name = "player_out")
    private PlayerEntity playerEntityOut;
}
