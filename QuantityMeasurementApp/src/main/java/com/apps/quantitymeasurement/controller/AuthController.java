package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.AuthResponse;
import com.apps.quantitymeasurement.dto.LoginRequest;
import com.apps.quantitymeasurement.dto.RegisterRequest;
import com.apps.quantitymeasurement.entity.User;
import com.apps.quantitymeasurement.service.AuthService;
import com.apps.quantitymeasurement.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        User createdUser = authService.register(request);
        return ResponseEntity.ok(new AuthResponse(
                null,
                createdUser.getUsername(),
                "Account created successfully"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User validUser = authService.login(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(new AuthResponse(
                jwtUtil.generateToken(validUser.getUsername()),
                validUser.getUsername(),
                "Login successful"
        ));
    }
}
