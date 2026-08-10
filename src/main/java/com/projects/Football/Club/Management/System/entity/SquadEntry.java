package com.projects.Football.Club.Management.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"squad_id", "player_id"}))
public class SquadEntry{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SquadRole role;
}

