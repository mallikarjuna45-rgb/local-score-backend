package com.cricket.local_score.Entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String fullName;
}
