package com.projects.Football.Club.Management.System.controller;
import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/player")
    public List<Player> getPlayers(){
        return playerService.getPlayers();
    }

    @GetMapping("/player/{playerId}")
    public Player getPlayerById(@PathVariable int playerId){
        return playerService.getPlayerById(playerId);
    }

    @PostMapping("/player")
    public void addPlayer(@RequestBody Player player){
        playerService.addPlayer(player);
    }

    @PutMapping("/player")
    public void updatePlayer(@RequestBody Player player){
        playerService.updatePlayer(player);
    }

    @DeleteMapping("/player/{playerId}")
    public void deletePlayer(@PathVariable int playerId){
        playerService.deletePlayer(playerId);
    }

    @PutMapping("/player/assignPlayerToTeam/{teamId}/{playerId}")
    public void assignPlayerToTeam(@PathVariable int teamId,@PathVariable int playerId){
        playerService.assignPlayertoTeam(teamId,playerId);
    }

    @PutMapping("/player/removePlayerFromTeam/{teamId}/{playerId}")
    public void removePlayerFromTeam(@PathVariable int teamId,@PathVariable int playerId){
        playerService.removePlayerFromTeam(teamId,playerId);
    }

    @PutMapping("/player/transferPlayer/{teamId1}/{teamId2}/{playerId}")
    public void transferPlayer(@PathVariable int teamId1,@PathVariable int teamId2,@PathVariable int playerId){
        playerService.transferPlayer(teamId1,teamId2,playerId);
    }
}