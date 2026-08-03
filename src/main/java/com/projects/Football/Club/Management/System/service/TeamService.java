package com.projects.Football.Club.Management.System.service;


import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepo repo;

    public List<Team> getAllTeams() {
        return repo.findAll();
    }

    public Team getTeamById(int teamId) {
        return repo.findById(teamId).orElse(new Team());
    }
    public void addTeam( Team team){
        repo.save(team);
    }
    public void updateTeam( Team team){
        repo.save(team);
    }
    public void deletePlayer(int teamId){
        repo.deleteById(teamId);
    }
}
