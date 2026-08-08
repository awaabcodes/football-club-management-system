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
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "squad", fetch = FetchType.LAZY)
    private List<SquadEntry> squad;
}
