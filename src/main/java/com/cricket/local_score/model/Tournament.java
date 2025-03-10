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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Date startDate;
    private Date endDate;
    private Integer totalTeams;
    private Integer priceMoney;
    private Integer totalSixs;
    private Integer totalFours;

    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    @JsonIgnore 
    private Team winnerTeam;

    @ManyToOne
    @JsonIgnore 
    @JoinColumn(name = "runner_team_id")
    private Team runnerTeam;

    @ManyToOne
    @JoinColumn(name = "player_of_tournament")
    @JsonIgnore 
    private Player playerOfTournament;

    @ManyToMany
    @JoinTable(
        name = "tournament_team",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    private String Status;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    
    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;
}


