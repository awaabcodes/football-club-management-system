package com.projects.Football.Club.Management.System.service;


import com.projects.Football.Club.Management.System.entity.Coach;

import com.projects.Football.Club.Management.System.repository.CoachRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    CoachRepo coachRepo;

    public List<Coach> getAllCoach() {
        return coachRepo.findAll();
    }

    public Coach getCoachById(int coachId) {
        return coachRepo.findById(coachId).orElseThrow();
    }

    public void addCoach(Coach coach) {
        coachRepo.save(coach);
    }

    public void updateCoach(Coach coach) {
        coachRepo.save(coach);
    }

    public void deleteCoach(int coachId) {
        Coach coach = coachRepo.findById(coachId).orElseThrow();
        if(coach.getTeam() == null)
        coachRepo.deleteById(coachId);
    }
}
