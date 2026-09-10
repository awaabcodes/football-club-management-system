package com.projects.Football.Club.Management.System.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtService {
    public String extractUsername(String token) {
        return null;
    }
    public Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }
}
