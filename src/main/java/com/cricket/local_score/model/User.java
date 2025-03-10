package com.cricket.local_score.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String fullname;
    private String status; // Example: "ACTIVE", "INACTIVE", "BANNED"

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private Set<Tournament> tournaments = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private Set<Game> matches = new HashSet<>();
}

