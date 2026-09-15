package com.bpost.securitydemo.service;

import com.bpost.securitydemo.utility.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {
    public  final String  secreat = "hellsravani12345678901234567890745861237458";
    private final SecretKey  secretKey =
            Keys.hmacShaKeyFor(secreat.getBytes(StandardCharsets.UTF_8));

    public String generateJwtToken(LoginRequest loginRequest){
        return Jwts.builder()
                .subject(loginRequest.getUserName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(secretKey)
                .compact();
    }

    public boolean isValid(String token, UserDetails userDetails) {
        String userName = extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpiry(token);

        }


    public boolean isTokenExpiry(String token){

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
       return claims.getSubject();
    }
}
