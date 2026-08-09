package com.projects.Football.Club.Management.System.controller;

import com.projects.Football.Club.Management.System.service.SquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/squad")
public class SquadController {

    @Autowired
    SquadService squadService;

    @PostMapping
    public void createSquad(){
        squadService.createSquad();
    }

}
