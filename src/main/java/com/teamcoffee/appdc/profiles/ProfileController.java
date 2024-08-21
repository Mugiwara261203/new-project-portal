package com.teamcoffee.appdc.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PutMapping("/update/paciente/{idUser}")
    public ResponseEntity<?> updatePacienteProfile(@PathVariable Integer idUser, @RequestBody Paciente perfil){
        Paciente result = profileService.updatePacienteProfile(perfil, idUser);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/medico/{idUser}")
    public ResponseEntity<?> updateMedicoProfile(@PathVariable Integer idUser, @RequestBody Medico perfil){
        Medico result = profileService.updateMedicoProfile(perfil, idUser);
        return ResponseEntity.ok(result);
    }
}
