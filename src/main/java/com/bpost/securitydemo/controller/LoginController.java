package com.bpost.securitydemo.controller;

import com.bpost.securitydemo.service.JwtService;
import com.bpost.securitydemo.utility.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }


    @PostMapping("/login")
    public String generateJwtToken(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        } catch (Exception e) {
            e.getMessage();
        }

        return jwtService.generateJwtToken(loginRequest);
    }
}
