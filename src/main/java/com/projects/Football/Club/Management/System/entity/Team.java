package com.projects.Football.Club.Management.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ageGroup;

    @Column(nullable = false)
    private String headCoach;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players;
}
