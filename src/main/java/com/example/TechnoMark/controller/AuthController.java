package com.example.TechnoMark.controller;

import com.example.TechnoMark.model.AuthResponse;
import com.example.TechnoMark.model.Customer;
import com.example.TechnoMark.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Customer customer) {
        return ResponseEntity.ok(authenticationService.register(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Customer customer) {
        return ResponseEntity.ok(authenticationService.login(customer));
    }

    @GetMapping("/dummy")
    public ResponseEntity<String> dummy() {
        return ResponseEntity.ok("selamlar");
    }
}
