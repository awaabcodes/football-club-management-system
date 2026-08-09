package com.projects.Football.Club.Management.System.service;

import com.projects.Football.Club.Management.System.entity.Squad;
import com.projects.Football.Club.Management.System.repository.SquadRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class SquadService {

    @Autowired
    SquadRepo squadRepo;

    public void createSquad() {
        squadRepo.save(new Squad());
    }
}
