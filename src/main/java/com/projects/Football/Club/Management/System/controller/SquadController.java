package com.projects.Football.Club.Management.System.controller;

import com.projects.Football.Club.Management.System.entity.SquadEntry;
import com.projects.Football.Club.Management.System.service.SquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/squad")
public class SquadController {

    @Autowired
    SquadService squadService;

    @PostMapping("/{teamId}")
    public void createSquad(@PathVariable int teamId){
        squadService.createSquad(teamId);
    }
    @PutMapping("/player")
    public void addPlayer(@RequestBody SquadEntry squadEntry){
        squadService.addPlayer(squadEntry);
    }
    @DeleteMapping("/player/{squadEntryId}")
    public void removePlayer(@PathVariable int squadEntryId){
        squadService.removePlayer(squadEntryId);
    }
    @PutMapping("/player/roles/{squadEntryid1}/{squadEntryid2}")
    public void swapRole(@PathVariable int squadEntryId1, @PathVariable int squadEntryId2){
        squadService.swapRoles(squadEntryId1,squadEntryId2);
    }
}
