package com.projects.Football.Club.Management.System.controller;

import com.projects.Football.Club.Management.System.dto.LoginRequest;
import com.projects.Football.Club.Management.System.dto.LoginResponse;
import com.projects.Football.Club.Management.System.dto.RegisterRequest;
import com.projects.Football.Club.Management.System.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
