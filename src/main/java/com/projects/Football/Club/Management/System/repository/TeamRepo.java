package com.projects.Football.Club.Management.System.repository;

import com.projects.Football.Club.Management.System.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Team,Integer> {
}
