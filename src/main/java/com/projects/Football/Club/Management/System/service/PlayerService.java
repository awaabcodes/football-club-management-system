package com.projects.Football.Club.Management.System.service;

import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.entity.Team;
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
        return playerRepo.findById(playerId).orElse(new Player());
    }

    public void addPlayer(Player player) {
        playerRepo.save(player);
    }
    public void updatePlayer(Player player) {
        playerRepo.save(player);
    }

    public void deletePlayer(int playerId) {
        playerRepo.deleteById(playerId);
    }

    public void assignPlayertoTeam(int teamId, int playerId) {
        Team team = teamRepo.findById(teamId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Player player = playerRepo.findById(playerId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if(team.getPlayers().size() < maxSquadSize) {
            if (player.getTeam() == null) {
                if(playerRepo.existsByTeamIdAndJerseyNumber(teamId,player.getJerseyNumber()))
                    return;
                player.setTeam(team);
                team.getPlayers().add(player);
                playerRepo.save(player);
            }
        }
    }

    public void removePlayerFromTeam(int teamId, int playerId) {
        Team team = teamRepo.findById(teamId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Player player = playerRepo.findById(playerId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if(player.getTeam() != null){
            if(player.getTeam().getId().equals(team.getId())){
                team.getPlayers().remove(player);
                player.setTeam(null);
                playerRepo.save(player);
            }
        }
    }

    public void transferPlayer(int teamId1, int teamId2, int playerId) {
        Team team1 = teamRepo.findById(teamId1).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Team team2 = teamRepo.findById(teamId2).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Player player = playerRepo.findById(playerId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if(player.getTeam() == null)
            return;
        if(!player.getTeam().getId().equals(team1.getId()))
            return;
        if(team1.getId() == team2.getId())
            return;
        if(!(team2.getPlayers().size() < maxSquadSize))
            return;
        if(playerRepo.existsByTeamIdAndJerseyNumber(teamId2,player.getJerseyNumber()))
            return;

        team1.getPlayers().remove(player);
        team2.getPlayers().add(player);
        player.setTeam(team2);
        playerRepo.save(player);
    }
}