package com.projects.Football.Club.Management.System.service;


import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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

    public void deleteTeam(int teamId){
        if(repo.existsById(teamId))
            return;
        repo.deleteById(teamId);
    }
}
