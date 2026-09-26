package com.projects.Football.Club.Management.System.dto;

import com.projects.Football.Club.Management.System.entity.AgeGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamRequest {
    @NotBlank
    private String name;
    @NotNull
    private AgeGroup ageGroup;
}
