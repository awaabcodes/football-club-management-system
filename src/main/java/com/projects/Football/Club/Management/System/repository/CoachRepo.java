package com.projects.Football.Club.Management.System.repository;

import com.projects.Football.Club.Management.System.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepo extends JpaRepository<Coach,Integer> {
}
