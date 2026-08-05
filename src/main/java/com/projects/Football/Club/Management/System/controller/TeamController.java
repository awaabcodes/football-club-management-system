package com.projects.Football.Club.Management.System.controller;

import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamService service;

    @GetMapping("/team")
    public List<Team> getAllTeams(){
        return service.getAllTeams();
    }
    @GetMapping("/team/{teamId}")
    public Team getTeamById(@PathVariable int teamId){
        return service.getTeamById(teamId);
    }
    @PostMapping("/team")
    public void addTeam(@RequestBody Team team){
        service.addTeam(team);
    }
    @PutMapping("/team")
    public void updateTeam(@RequestBody Team team){
        service.updateTeam(team);
    }
    @DeleteMapping("/team/{teamId}")
    public void deleteTeam(@PathVariable int teamId){
        service.deleteTeam(teamId);
    }

    @PutMapping("/team/assignPlayer/{teamId}/{playerId}")
    public void assignPlayerToTeam(@PathVariable int teamId,@PathVariable int playerId){
        service.assignPlayertoTeam(teamId,playerId);
    }
    @PutMapping("/team/removePlayerFromTeam/{teamId}/{playerId}")
    public void removePlayerFromTeam(@PathVariable int teamId,@PathVariable int playerId){
        service.removePlayerFromTeam(teamId,playerId);
    }
    @PutMapping("/team/transferPlayer/{teamId1}/{teamId2}/{playerId}")
    public void transferPlayer(@PathVariable int teamId1,@PathVariable int teamId2,@PathVariable int playerId){
        service.transferPlayer(teamId1,teamId2,playerId);
    }
}
