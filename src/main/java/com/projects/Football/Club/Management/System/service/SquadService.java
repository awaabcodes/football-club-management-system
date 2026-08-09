package com.projects.Football.Club.Management.System.service;

import com.projects.Football.Club.Management.System.entity.Squad;
import com.projects.Football.Club.Management.System.entity.SquadEntry;
import com.projects.Football.Club.Management.System.entity.SquadRole;
import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.repository.SquadEntryRepo;
import com.projects.Football.Club.Management.System.repository.SquadRepo;
import com.projects.Football.Club.Management.System.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SquadService {

    @Autowired
    SquadRepo squadRepo;

    @Autowired
    SquadEntryRepo squadEntryRepo;

    @Autowired
    TeamRepo teamRepo;

    // controller linked methods
    public void createSquad(int teamId) {
        Team team = teamRepo.findById(teamId).orElseThrow();
        Squad squad = new Squad();
        squad.setTeam(team);
        squad.setSquadEntries(new ArrayList<>());

        squadRepo.save(squad);
    }

    public void addPlayer(SquadEntry squadEntry) {
        if (!checkTeamMemberShip(squadEntry))
            return;
        if (!squadSizeCheck(squadEntry))
            return;
        if (squadEntry.getRole().equals(SquadRole.STARTER)){
        if (!checkStarterPlayersLimit(squadEntry))
            return;
        if (checkSubPlayersLimit(squadEntry)) {
            squadEntry.getSquad().getSquadEntries().add(squadEntry);
            saveSquadEntry(squadEntry);
        }
        }


    }

    public void removePlayer(int squadEntryId) {
        squadEntryRepo.deleteById(squadEntryId);

    }

    public void swapRoles(int squadEntryId1, int squadEntryId2) {
        SquadEntry squadEntry1 = squadEntryRepo.findById(squadEntryId1).orElseThrow();
        SquadEntry squadEntry2 = squadEntryRepo.findById(squadEntryId2).orElseThrow();

        //check if in the same squad
        if (!squadEntry1.getSquad().equals(squadEntry2.getSquad()))
            return;
        //check if players have different roles
        if (squadEntry1.getRole().equals(squadEntry2.getRole()))
            return;
        SquadRole squadRole = squadEntry1.getRole();
        squadEntry1.setRole(squadEntry2.getRole());
        squadEntry2.setRole(squadRole);
        saveSquadEntry(squadEntry1);
        saveSquadEntry(squadEntry2);
    }

    //methods for reusability
    public boolean checkTeamMemberShip(SquadEntry squadEntry) {
        return squadEntry.getPlayer().getTeam().equals(squadEntry.getSquad().getTeam());
    }

    public void saveSquadEntry(SquadEntry squadEntry) {
        squadEntryRepo.save(squadEntry);
        squadRepo.save(squadEntry.getSquad());
    }

    public boolean squadSizeCheck(SquadEntry squadEntry) {
        return squadEntry.getSquad().getSquadEntries().size() < 20;
    }

    public boolean checkStarterPlayersLimit(SquadEntry squadEntry) {
        int max = 0;
        for (int i = 0; i < squadEntry.getSquad().getSquadEntries().size(); i++) {
            if (squadEntry.getSquad().getSquadEntries().get(i).getRole().equals(SquadRole.STARTER))
                max++;
        }
        return max < 11;
    }

    private boolean checkSubPlayersLimit(SquadEntry squadEntry) {
        int max = 0;
        for (int i = 0; i < squadEntry.getSquad().getSquadEntries().size(); i++) {
            if (squadEntry.getSquad().getSquadEntries().get(i).getRole().equals(SquadRole.SUBSTITUTE))
                max++;
        }
        return max < 9;
    }
}
