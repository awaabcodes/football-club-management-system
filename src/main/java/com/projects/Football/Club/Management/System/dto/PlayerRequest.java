package com.projects.Football.Club.Management.System.dto;

import com.projects.Football.Club.Management.System.entity.Position;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRequest {
    @NotBlank
    private String name;
    @Min(15)
    @Max(45)
    private int age;
    @NotNull
    private Position position;
    @NotBlank
    @Positive
    @Max(99)
    private int jerseyNumber;
}
