package com.projects.Football.Club.Management.System.service;

import com.projects.Football.Club.Management.System.entity.Squad;
import com.projects.Football.Club.Management.System.entity.SquadEntry;
import com.projects.Football.Club.Management.System.entity.SquadRole;
import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.exception.InvalidOperation;
import com.projects.Football.Club.Management.System.exception.ResourceNotFound;
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
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new ResourceNotFound("Team not found with id: " + teamId));
        Squad squad = new Squad();
        squad.setTeam(team);
        squad.setSquadEntries(new ArrayList<>());

        squadRepo.save(squad);
    }

    public void addPlayer(SquadEntry squadEntry) {
        validateTeamMembership(squadEntry);
        validateSquadSize(squadEntry);

        if (squadEntry.getRole() == SquadRole.STARTER)
            validateStarterLimit(squadEntry);
        else
            validateSubLimit(squadEntry);

        squadEntry.getSquad().getSquadEntries().add(squadEntry);
        saveSquadEntry(squadEntry);
    }

    public void removePlayer(int squadEntryId) {
        if (!squadEntryRepo.existsById(squadEntryId))
            throw new ResourceNotFound("Squad entry not found with id: " + squadEntryId);
        squadEntryRepo.deleteById(squadEntryId);
    }

    public void swapRoles(int squadEntryId1, int squadEntryId2) {
        SquadEntry squadEntry1 = squadEntryRepo.findById(squadEntryId1)
                .orElseThrow(() -> new ResourceNotFound("Squad entry not found with id: " + squadEntryId1));
        SquadEntry squadEntry2 = squadEntryRepo.findById(squadEntryId2)
                .orElseThrow(() -> new ResourceNotFound("Squad entry not found with id: " + squadEntryId2));

        if (!squadEntry1.getSquad().equals(squadEntry2.getSquad()))
            throw new InvalidOperation("Squad entries belong to different squads");
        if (squadEntry1.getRole().equals(squadEntry2.getRole()))
            throw new InvalidOperation("Entries already have different roles");

        SquadRole squadRole = squadEntry1.getRole();
        squadEntry1.setRole(squadEntry2.getRole());
        squadEntry2.setRole(squadRole);
        saveSquadEntry(squadEntry1);
        saveSquadEntry(squadEntry2);
    }

    // methods for reusability
    public void validateTeamMembership(SquadEntry squadEntry) {
        if (!squadEntry.getPlayer().getTeam().equals(squadEntry.getSquad().getTeam()))
            throw new InvalidOperation("Player does not belong to the team for this squad");
    }

    public void saveSquadEntry(SquadEntry squadEntry) {
        squadEntryRepo.save(squadEntry);
        squadRepo.save(squadEntry.getSquad());
    }

    public void validateSquadSize(SquadEntry squadEntry) {
        if (squadEntry.getSquad().getSquadEntries().size() >= 20)
            throw new InvalidOperation("Squad has reached its maximum size of 20 entries");
    }

    public void validateStarterLimit(SquadEntry squadEntry) {
        int count = 0;
        for (int i = 0; i < squadEntry.getSquad().getSquadEntries().size(); i++) {
            if (squadEntry.getSquad().getSquadEntries().get(i).getRole().equals(SquadRole.STARTER))
                count++;
        }
        if (count >= 11)
            throw new InvalidOperation("Squad already has 11 starters");
    }

    private void validateSubLimit(SquadEntry squadEntry) {
        int count = 0;
        for (int i = 0; i < squadEntry.getSquad().getSquadEntries().size(); i++) {
            if (squadEntry.getSquad().getSquadEntries().get(i).getRole().equals(SquadRole.SUBSTITUTE))
                count++;
        }
        if (count >= 9)
            throw new InvalidOperation("Squad already has 9 substitutes");
    }
}