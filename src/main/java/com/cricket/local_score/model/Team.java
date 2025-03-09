package com.cricket.local_score.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int matches;
    private int wins;
    private int loss;
    private int draw;
    private int size;

    @ManyToMany(mappedBy="teams")
    @JsonIgnore 
    private Set<Player> players = new HashSet<>();

    @ManyToMany(mappedBy = "teams")
    @JsonIgnore 
    private Set<Tournament> tournaments = new HashSet<>();
}
