package com.cricket.local_score.dto;

import com.cricket.local_score.Entity.TeamEntity;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class playerDto {
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
    private Set<TeamEntity> teamEntities = new HashSet<>();
}
