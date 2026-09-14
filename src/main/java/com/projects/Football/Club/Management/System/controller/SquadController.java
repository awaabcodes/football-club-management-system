package com.projects.Football.Club.Management.System.controller;

import com.projects.Football.Club.Management.System.dto.AddPlayerRequest;
import com.projects.Football.Club.Management.System.entity.SquadEntry;
import com.projects.Football.Club.Management.System.service.SquadService;
import jakarta.validation.Valid;
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
    public void addPlayer(@Valid AddPlayerRequest request ){
        squadService.addPlayer(request);
    }
    @DeleteMapping("/player/{squadEntryId}")
    public void removePlayer(@PathVariable int squadEntryId){
        squadService.removePlayer(squadEntryId);
    }

    @PutMapping("/player/roles/{squadEntryId1}/{squadEntryId2}")
    public void swapRole(@PathVariable int squadEntryId1, @PathVariable int squadEntryId2){
        squadService.swapRoles(squadEntryId1,squadEntryId2);
    }
}
