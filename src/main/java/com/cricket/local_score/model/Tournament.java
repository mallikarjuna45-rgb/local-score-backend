package com.cricket.local_score.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Date startDate;
    private Date endDate;
    private int totalTeams;
    private int priceMoney;
    private int totalSixs;
    private int totalFours;

    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    @JsonIgnore 
    private Team winnerTeam;

    @ManyToOne
    @JsonIgnore 
    @JoinColumn(name = "runner_team_id")
    private Team runnerTeam;

    @ManyToOne
    @JoinColumn(name = "player_of_tournament_id")
    @JsonIgnore 
    private Player playerOfTournament;

    @ManyToMany
    @JoinTable(
        name = "tournament_team",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "tournament_player",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> players = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}


