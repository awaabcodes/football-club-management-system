package com.projects.Football.Club.Management.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SquadEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private SquadRole role;
}

enum SquadRole{
        STARTER,
        SUBSTITUTE
}
