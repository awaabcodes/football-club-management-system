package com.projects.Football.Club.Management.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.management.relation.Role;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true , nullable = false)
    private String username;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String password;

    @Enumerated (EnumType.STRING)
    @NotNull
    private Role role;



}
