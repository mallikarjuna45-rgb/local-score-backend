package com.cricket.local_score.Entity;

import java.util.HashSet;
import java.util.Set;

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
public class PlayerEntity {
    @Id
    private Integer id;
    private String name;
    private Integer runs;
    private Integer boundaries;
    private Integer ballsFaced;
    private Integer ballsBowled;
    private Integer wickets;
    private Integer matches;
    private Float battingStrikeRate;
    private Float bowlingStrikeRate;
    private Integer catches;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserEntity user;
    @ManyToMany
    @JoinTable(
        name = "player_team",
        joinColumns = @JoinColumn(name = "player_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<TeamEntity> teamEntities = new HashSet<>();
}

