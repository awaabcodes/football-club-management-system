package com.projects.Football.Club.Management.System.service;

import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.entity.Team;
import com.projects.Football.Club.Management.System.exception.DuplicateResource;
import com.projects.Football.Club.Management.System.exception.InvalidOperation;
import com.projects.Football.Club.Management.System.exception.ResourceNotFound;
import com.projects.Football.Club.Management.System.repository.PlayerRepo;
import com.projects.Football.Club.Management.System.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    TeamRepo teamRepo;
    final private int maxSquadSize = 25;

    public List<Player> getPlayers() {
        return playerRepo.findAll();
    }

    public Player getPlayerById(int playerId) {
        return playerRepo.findById(playerId).orElseThrow(() ->new ResourceNotFound("Player not found with this id: " + playerId));
    }

    public void addPlayer(Player player) {
        playerRepo.save(player);
    }
    public void updatePlayer(Player player) {
        playerRepo.save(player);
    }

    public void deletePlayer(int playerId) {
        if(!playerRepo.existsById(playerId))
            throw new ResourceNotFound("Player not found with this id: " + playerId);
        playerRepo.deleteById(playerId);
    }

    public void assignPlayertoTeam(int teamId, int playerId) {
        Team team = teamRepo.findById(teamId).orElseThrow(()->new ResourceNotFound("Team not found with this id: " + teamId));
        Player player = playerRepo.findById(playerId).orElseThrow(()->new ResourceNotFound("Player not found with this id: " + playerId));
        if(team.getPlayers().size() >= maxSquadSize)
            throw new InvalidOperation("Team"+ teamId +"has reached maximum Squad size of " + maxSquadSize);
            if (player.getTeam() != null)
                throw new InvalidOperation("Player" + playerId + "is already assign to another team");
                if(playerRepo.existsByTeamIdAndJerseyNumber(teamId,player.getJerseyNumber()))
                    throw new DuplicateResource("Jersey Number" + player.getJerseyNumber() + "is already assigned to a player on the team" );
                player.setTeam(team);
                team.getPlayers().add(player);
                playerRepo.save(player);
        }

    public void removePlayerFromTeam(int teamId, int playerId) {
        Team team = teamRepo.findById(teamId).orElseThrow(()->new ResourceNotFound("Team not found with this id: " + teamId));
        Player player = playerRepo.findById(playerId).orElseThrow(()->new ResourceNotFound("Player not found with this id: " + playerId));
        if(player.getTeam() == null)
            throw new InvalidOperation("Player " + playerId + " is not assigned to any team");
            if(player.getTeam().getId().equals(team.getId()))
                throw new InvalidOperation("Player " + playerId + "is not assigned to team " + teamId);
                team.getPlayers().remove(player);
                player.setTeam(null);
                playerRepo.save(player);
    }

    public void transferPlayer(int teamId1, int teamId2, int playerId) {
        Team team1 = teamRepo.findById(teamId1).orElseThrow(()->new ResourceNotFound("Team not found with this id: " + teamId1));
        Team team2 = teamRepo.findById(teamId2).orElseThrow(()->new ResourceNotFound("Team not found with this id: " + teamId2));
        Player player = playerRepo.findById(playerId).orElseThrow(()->new ResourceNotFound("Player not found with this id: " + playerId));
        if(player.getTeam() == null)
            throw new InvalidOperation("Player " + playerId + "is not in any team ");
        if(!player.getTeam().getId().equals(team1.getId()))
            throw new InvalidOperation("Player " + playerId + "is not currently in team " + teamId1);
        if(team1.getId() == team2.getId())
            throw new DuplicateResource("The current team and the target team are the same");// Invalid Argument Exception
        if(!(team2.getPlayers().size() < maxSquadSize))
            throw new InvalidOperation("Team"+ teamId2 +"has reached maximum Squad size of " + maxSquadSize);
        if(playerRepo.existsByTeamIdAndJerseyNumber(teamId2,player.getJerseyNumber()))
            throw new DuplicateResource("Jersey Number" + player.getJerseyNumber() + "is already assigned to a player on the team" );

        team1.getPlayers().remove(player);
        team2.getPlayers().add(player);
        player.setTeam(team2);
        playerRepo.save(player);
    }
}