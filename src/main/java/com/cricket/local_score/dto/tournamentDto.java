package com.cricket.local_score.dto;

import com.cricket.local_score.Entity.AddressEntity;
import com.cricket.local_score.Entity.PlayerEntity;
import com.cricket.local_score.Entity.TeamEntity;
import com.cricket.local_score.Entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
public class tournamentDto {

    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Integer totalTeams;
    private Integer priceMoney;
    private Integer totalSixs;
    private Integer totalFours;


    private teamDto winnerTeam;
    private teamDto runnerTeam;
    private playerDto playerOfTournament;

    private Set<teamDto> teams;

    private String status;

    private UserEntity owner;
    private AddressEntity address;
}
