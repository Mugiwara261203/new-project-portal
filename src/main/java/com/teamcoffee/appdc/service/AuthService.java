package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.dto.AuthResponse;
import com.teamcoffee.appdc.dto.AuthenticationRequest;
import com.teamcoffee.appdc.dto.RegisterRequest;
import com.teamcoffee.appdc.persistence.entity.Role;
import com.teamcoffee.appdc.persistence.entity.User;
import com.teamcoffee.appdc.persistence.repository.RoleRepository;
import com.teamcoffee.appdc.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public AuthResponse register(RegisterRequest request) {
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Set.of(userRole));

        user = userRepository.save(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());

        String accessToken = jwtService.generateToken(userDetails, "ROLE_USER", user.getId());
        String refreshToken = jwtService.generateRefreshToken(userDetails, user.getId());

        return new AuthResponse(accessToken, refreshToken);

    }

    public AuthResponse authenticate(AuthenticationRequest request) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String role = user.getRole().stream().findFirst().get().getName();
        Long userId = user.getId();

        String accessToken = jwtService.generateToken(userDetails, role, userId);
        String refreshToken = jwtService.generateRefreshToken(userDetails, userId);

        return new AuthResponse(accessToken, refreshToken);
    }
}
