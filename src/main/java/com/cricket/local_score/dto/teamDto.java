package com.cricket.local_score.dto;

import com.cricket.local_score.Entity.PlayerEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class teamDto {
    private Integer id;
    private String name;
    private Integer matches;
    private Integer wins;
    private Integer loss;
    private Integer draw;
    private Integer size;
    private Set<PlayerEntity> playerEntities = new HashSet<>();
}
