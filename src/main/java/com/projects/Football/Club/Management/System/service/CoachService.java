package com.projects.Football.Club.Management.System.service;


import com.projects.Football.Club.Management.System.entity.Coach;

import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.exception.DuplicateResource;
import com.projects.Football.Club.Management.System.exception.InvalidOperation;
import com.projects.Football.Club.Management.System.exception.ResourceNotFound;
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
        return coachRepo.findById(coachId).orElseThrow(() ->new ResourceNotFound("Coach not found with this id: " + coachId));
    }

    public void addCoach(Coach coach) {
        coachRepo.save(coach);
    }

    public void updateCoach(Coach coach) {
        coachRepo.save(coach);
    }

    public void deleteCoach(int coachId) {
        Coach coach = coachRepo.findById(coachId).orElseThrow(() ->new ResourceNotFound("Coach not found with this id: " + coachId));
        if(coach.getTeam() != null)
            throw new InvalidOperation("Coach with this id " + coachId + "is assigned to a team");
        coachRepo.deleteById(coachId);
    }

    public void assignCoach(int teamId, int coachId) {
        Team team = teamRepo.findById(teamId).orElseThrow(()->new ResourceNotFound("Team not found with this id: " + teamId));
        Coach coach = coachRepo.findById(coachId).orElseThrow(()->new ResourceNotFound("Coach not found with this id: " + coachId));
        if (coach.getTeam() != null)
            throw new InvalidOperation("Coach is Already assigned to a team");
        if (team.getCoach() != null)
            throw new InvalidOperation("Team Already has a coach with this id "+ team.getCoach().getId());
        team.setCoach(coach);
        coach.setTeam(team);
        coachRepo.save(coach);

    }
    public void removeCoach(int teamId, int coachId) {
        Team team = teamRepo.findById(teamId).orElseThrow(()->new ResourceNotFound("Team not found with this id: " + teamId));
        Coach coach = coachRepo.findById(coachId).orElseThrow(()->new ResourceNotFound("Coach not found with this id: " + coachId));
        if (coach.getTeam() == null)
            throw new InvalidOperation("Coach is not assigned to any team");
        if (team.getCoach() == null)
            throw new InvalidOperation("Team does not have any coach to be removed");
        if(!coach.getTeam().getId().equals(team.getId()))
            throw new InvalidOperation("Team does not have any coach with this id "+ coachId );
        team.setCoach(null);
        coach.setTeam(null);
        coachRepo.save(coach);
    }

    public void transferCoach(int teamId1, int teamId2, int coachId) {
        Team team1 = teamRepo.findById(teamId1).orElseThrow(()->new ResourceNotFound("Team not found with this id: " + teamId1));
        Team team2 = teamRepo.findById(teamId2).orElseThrow(()->new ResourceNotFound("Team not found with this id: " + teamId2));
        Coach coach = coachRepo.findById(coachId).orElseThrow(()->new ResourceNotFound("Coach not found with this id: " + coachId));

        if(coach.getTeam() == null)
            throw new InvalidOperation("Coach is not assigned to any team");
        if(!coach.getTeam().getId().equals(team1.getId()))
            throw new InvalidOperation("Team does not have any coach with this id "+ coachId );
        if(team2.getCoach() != null)
            throw new InvalidOperation("Target team have a coach with this id "+ team2.getCoach().getId() );
        if(team1.getId().equals(team2.getId()))
            throw new InvalidOperation("You cannot transfer coach from the same to the same team");
        team1.setCoach(null);
        team2.setCoach(coach);
        coach.setTeam(team2);
        coachRepo.save(coach);
    }

}


