package com.projects.Football.Club.Management.System.controller;

import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;



    @GetMapping("/team")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/team/{teamId}")
    public Team getTeamById(@PathVariable int teamId){
        return teamService.getTeamById(teamId);
    }

    @PostMapping("/team")
    public void addTeam(@RequestBody Team team){
        teamService.addTeam(team);
    }

    @PutMapping("/team")
    public void updateTeam(@RequestBody Team team){
        teamService.updateTeam(team);
    }

    @DeleteMapping("/team/{teamId}")
    public void deleteTeam(@PathVariable int teamId){
        teamService.deleteTeam(teamId);
    }

}
