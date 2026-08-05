package com.projects.Football.Club.Management.System.service;


import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.repository.PlayerRepo;
import com.projects.Football.Club.Management.System.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepo repo;

    @Autowired
    PlayerRepo playerRepo;

    final private int maxSquadSize = 25;

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

    public void assignPlayertoTeam(int teamId, int playerId) {
        Team team = repo.findById(teamId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Player player = playerRepo.findById(playerId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if(team.getPlayers().size() < maxSquadSize) {
            if (player.getTeam() == null) {
                player.setTeam(team);
                team.getPlayers().add(player);
                playerRepo.save(player);
            }
        }
    }
}
