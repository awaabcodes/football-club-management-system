package com.projects.Football.Club.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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


    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private AgeGroup ageGroup;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Player> players;

    @OneToOne(mappedBy = "team", fetch = FetchType.LAZY)
    private Coach coach;

    @OneToOne(mappedBy = "team", fetch = FetchType.LAZY)
    private Squad squad;

}
