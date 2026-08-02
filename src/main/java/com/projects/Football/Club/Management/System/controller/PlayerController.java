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

}