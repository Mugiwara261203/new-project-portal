package com.teamcoffee.appdc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcoffee.appdc.dto.AuthenticationRequest;
import com.teamcoffee.appdc.dto.AuthenticationResponse;
import com.teamcoffee.appdc.dto.RegisterRequest;
import com.teamcoffee.appdc.persistence.entity.Doctor;
import com.teamcoffee.appdc.persistence.entity.Patient;
import com.teamcoffee.appdc.persistence.entity.Role;
import com.teamcoffee.appdc.persistence.entity.User;
import com.teamcoffee.appdc.persistence.repository.DoctorRepository;
import com.teamcoffee.appdc.persistence.repository.PatientRepository;
import com.teamcoffee.appdc.persistence.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .enabled(request.isEnabled())
                .build();
        var saveUser = userRepository.save(user);

        if (request.getRole().equals("PATIENT")){
            var patient = Patient.builder()
                    .user(saveUser)
                    .diabetesType(request.getDiabetesType())
                    .gender(request.getGender())
                    .birthDate(request.getBirthDate())
                    .build();
            patientRepository.save(patient);
        } else if (request.getRole().equals("DOCTOR")){
            var doctor = Doctor.builder()
                    .user(saveUser)
                    .specialty(request.getSpecialty())
                    .doctorId(request.getDoctorId())
                    .build();
            doctorRepository.save(doctor);
        }

        var jwtToken = jwtService.generateToken(saveUser, saveUser.getRole().name(), saveUser.getId());
        var refreshToken = jwtService.generateRefreshToken(saveUser, saveUser.getId());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user, user.getRole().name(), user.getId());
        var refreshToken = jwtService.generateRefreshToken(user, user.getId());

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null){
            var user = this.userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)){
                var accessToken = jwtService.generateToken(user, user.getRole().name(), user.getId());

                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}