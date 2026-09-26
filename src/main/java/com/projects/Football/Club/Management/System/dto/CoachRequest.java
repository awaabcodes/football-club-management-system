package com.projects.Football.Club.Management.System.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @PositiveOrZero
    @NotNull
    private double experienceYears;
}
