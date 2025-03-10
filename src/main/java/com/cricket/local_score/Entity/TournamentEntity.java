package com.cricket.local_score.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TournamentEntity {
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
    private TeamEntity winnerTeamEntity;

    @ManyToOne
    @JsonIgnore 
    @JoinColumn(name = "runner_team_id")
    private TeamEntity runnerTeamEntity;

    @ManyToOne
    @JoinColumn(name = "player_of_tournament")
    @JsonIgnore 
    private PlayerEntity playerEntityOfTournament;

    @ManyToMany
    @JoinTable(
        name = "tournament_team",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<TeamEntity> teamEntities = new HashSet<>();

    private String Status;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
    
    @ManyToOne
    @JoinColumn(name="address_id")
    private AddressEntity addressEntity;
}


