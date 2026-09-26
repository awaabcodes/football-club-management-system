package com.projects.Football.Club.Management.System.dto;


import com.projects.Football.Club.Management.System.entity.SquadRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddPlayerRequest {
        private int squadId;
        private int playerId;
        private SquadRole role;

}
//Squad Operation