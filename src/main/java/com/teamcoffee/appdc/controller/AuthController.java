package com.teamcoffee.appdc.controller;

import com.teamcoffee.appdc.dto.RegisterRequest;
import com.teamcoffee.appdc.dto.SuccessResponse;
import com.teamcoffee.appdc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(new SuccessResponse("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }
}
