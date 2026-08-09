package com.projects.Football.Club.Management.System.repository;

import com.projects.Football.Club.Management.System.entity.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadRepo extends JpaRepository<Squad,Integer> {
}
