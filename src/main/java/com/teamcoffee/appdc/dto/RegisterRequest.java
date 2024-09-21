package com.teamcoffee.appdc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    // for user
    private String username;
    private String phone;
    private String email;
    private String password;
    private String role;
    private boolean enabled;

    //for patients
    private String firstname;
    private String lastname;
    private String diabetesType;
    private String gender;

    //for doctors
    private String specialty;
    private String doctorId;
}
