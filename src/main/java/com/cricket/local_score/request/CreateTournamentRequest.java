package com.cricket.local_score.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateTournamentRequest {

    private String name;
    private Date startDate;
    private Date endDate;
    private String status;
    private Integer ownerId;
    private Integer addressId;
}

