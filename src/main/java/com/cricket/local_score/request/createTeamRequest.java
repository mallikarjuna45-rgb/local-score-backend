package com.cricket.local_score.request;

import lombok.Data;

import java.util.List;

@Data
public class createTeamRequest {
    private String teamName;
    private List<Long> playerIds;
}
