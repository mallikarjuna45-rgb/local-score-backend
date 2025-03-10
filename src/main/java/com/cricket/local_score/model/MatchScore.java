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
public class MatchScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    
    @ManyToOne
    @JoinColumn(name = "tournment_id", nullable = false)
    private Tournament tournment;

    private Date date;
    
    @ManyToOne
    @JoinColumn(name = "batting_team", nullable = false)
    private Team battingTeam;
    
    @ManyToOne
    @JoinColumn(name = "bowling_team", nullable = false)
    private Team bowlingTeam;
    
    private Integer inningsNo;
    private Integer ballNo;
    
    @ManyToOne
    @JoinColumn(name = "bowler", nullable = false)
    private Player bowler;
    
    @ManyToOne
    @JoinColumn(name = "striker", nullable = false)
    private Player striker;
    
    @ManyToOne
    @JoinColumn(name = "non_striker", nullable = false)
    private Player nonStriker;
    
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
    private Player playerOut;
}
