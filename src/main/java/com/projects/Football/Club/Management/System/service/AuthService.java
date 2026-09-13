package com.projects.Football.Club.Management.System.service;

import com.projects.Football.Club.Management.System.dto.LoginRequest;
import com.projects.Football.Club.Management.System.dto.LoginResponse;
import com.projects.Football.Club.Management.System.dto.RegisterRequest;
import com.projects.Football.Club.Management.System.entity.Role;
import com.projects.Football.Club.Management.System.entity.User;
import com.projects.Football.Club.Management.System.exception.DuplicateResource;
import com.projects.Football.Club.Management.System.exception.ResourceNotFound;
import com.projects.Football.Club.Management.System.repository.UserRepo;
import com.projects.Football.Club.Management.System.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public void register(@Valid RegisterRequest request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent())
            throw new DuplicateResource("Username already taken: " + request.getUsername());
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.COACH);
        userRepo.save(user);
    }

    public LoginResponse login(@Valid LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()))

        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFound("User not found: " + request.getUsername()));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new LoginResponse(token);
    }
}
