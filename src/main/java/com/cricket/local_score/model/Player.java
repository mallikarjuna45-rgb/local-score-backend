package com.cricket.local_score.model;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int runs;
    private int boundaries;
    private int ballsFaced;
    private int ballsBowled;
    private int wickets;
    private int matches;
    private float battingStrikeRate;
    private float bowlingStrikeRate;
    private int catches;
    private int stumping;

    @ManyToMany
    @JoinTable(
        name = "player_team",
        joinColumns = @JoinColumn(name = "player_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    @ManyToMany(mappedBy = "players")
    @JsonIgnore
    private Set<Tournament> tournaments = new HashSet<>();
}

