package com.projects.Football.Club.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Team team;

    @OneToMany(mappedBy = "squad", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SquadEntry> squadEntries;
}
