package com.projects.Football.Club.Management.System.repository;

import com.projects.Football.Club.Management.System.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player,Integer> {
    boolean existsByTeamIdAndJerseyNumber(
            int teamId,
            int jerseyNumber
    );
}
