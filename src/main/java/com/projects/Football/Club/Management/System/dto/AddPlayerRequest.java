package com.projects.Football.Club.Management.System.dto;


import com.projects.Football.Club.Management.System.entity.SquadRole;

public class AddPlayerRequest {
        private int squadId;
        private int playerId;
        private SquadRole role;

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public SquadRole getRole() {
        return role;
    }

    public void setRole(SquadRole role) {
        this.role = role;
    }
}
