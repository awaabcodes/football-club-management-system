package com.projects.Football.Club.Management.System.service;


import com.projects.Football.Club.Management.System.entity.Coach;

import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.repository.CoachRepo;
import com.projects.Football.Club.Management.System.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    CoachRepo coachRepo;

    @Autowired
    TeamRepo teamRepo;


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

    public void assignCoach(int teamId, int coachId) {
        Team team = teamRepo.findById(teamId).orElseThrow();
        Coach coach = coachRepo.findById(coachId).orElseThrow();
        if (coach.getTeam() != null)
            return;
        if (team.getCoach() != null)
            return;
        team.setCoach(coach);
        coach.setTeam(team);
        coachRepo.save(coach);

    }
    public void removeCoach(int teamId, int coachId) {
        Team team = teamRepo.findById(teamId).orElseThrow();
        Coach coach = coachRepo.findById(coachId).orElseThrow();
        if (coach.getTeam() == null)
            return;
        if (team.getCoach() == null)
            return;
        if(!coach.getTeam().getId().equals(team.getId()))
            return;
        team.setCoach(null);
        coach.setTeam(null);
        coachRepo.save(coach);
    }

    public void transferCoach(int teamId1, int teamId2, int coachId) {
        Team team1 = teamRepo.findById(teamId1).orElseThrow();
        Team team2 = teamRepo.findById(teamId2).orElseThrow();
        Coach coach = coachRepo.findById(coachId).orElseThrow();

        if(coach.getTeam() == null)
            return;
        if(!coach.getTeam().getId().equals(team1.getId()))
            return;
        if(team2.getCoach() != null)
            return;
        if(team1.getId().equals(team2.getId()))
            return;
        team1.setCoach(null);
        team2.setCoach(coach);
        coach.setTeam(team2);
        coachRepo.save(coach);
    }

}


