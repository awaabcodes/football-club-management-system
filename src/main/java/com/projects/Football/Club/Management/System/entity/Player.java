package com.projects.Football.Club.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;


    @NotBlank
    @Column(nullable = false)
    private String name;

    @Min(15)
    @Max(45)
    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Position position;

    @Positive
    @Max(99)
    @Column(nullable = false)
    private int jerseyNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<SquadEntry> squadEntry;
}
