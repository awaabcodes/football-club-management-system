package com.projects.Football.Club.Management.System.repository;

import com.projects.Football.Club.Management.System.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player,Integer> {
}
