package com.bpost.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecController {
    @GetMapping("/test")
    public String test(){
        return "hello";
    }
}
