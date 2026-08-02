package com.projects.Football.Club.Management.System.service;

import com.projects.Football.Club.Management.System.entity.Player;
import com.projects.Football.Club.Management.System.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepo repo;


    public List<Player> getPlayers() {
        return repo.findAll();
    }

    public Player getPlayerById(int playerId) {
        return repo.findById(playerId).orElse(new Player());
    }

    public void addPlayer(Player player) {
        repo.save(player);
    }
    public void updatePlayer(Player player) {
        repo.save(player);
    }

    public void deletePlayer(int playerId) {
        repo.deleteById(playerId);
    }
}