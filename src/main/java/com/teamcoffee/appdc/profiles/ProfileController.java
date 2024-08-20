package com.teamcoffee.appdc.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PutMapping("/update/{idUser}")
    public ResponseEntity<?> updateProfile(@PathVariable Integer idUser, @RequestBody Object perfil, @RequestParam String role){
        Object result = profileService.updateProfile(perfil, idUser, role);
        return ResponseEntity.ok(result);
    }
}
