package com.teamcoffee.appdc.profiles;

import com.teamcoffee.appdc.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public Object updateProfile(Object profile, Integer idUser, String role){
        if (role.equals("PACIENTE")){
            Paciente paciente = (Paciente) profile;
            Optional<Paciente> existing = pacienteRepository.findByUserId(idUser);
            if (existing.isPresent()){
                Paciente patientExisting = existing.get();
                patientExisting.setUsername(paciente.getUsername());
                patientExisting.setLastname(paciente.getLastname());
                patientExisting.setDateBirth(paciente.getDateBirth());
                patientExisting.setAddress(paciente.getAddress());
                patientExisting.setPhoneNumber(paciente.getPhoneNumber());
                patientExisting.setTypeDiabetes(paciente.getTypeDiabetes());
                return pacienteRepository.save(patientExisting);
            } else {
                paciente.setUser(new User(idUser));
                return pacienteRepository.save(paciente);
            }
        } else if (role.equals("DOCTOR")) {
            Medico medico = (Medico) profile;
            Optional<Medico> existing = medicoRepository.findByUserId(idUser);
            if (existing.isPresent()){
                Medico medicExisting = existing.get();
                medicExisting.setUsername(medico.getUsername());
                medicExisting.setLastname(medico.getLastname());
                medicExisting.setSpecialization(medico.getSpecialization());
                medicExisting.setPhoneNumber(medico.getPhoneNumber());
                return medicoRepository.save(medicExisting);
            } else {
                medico.setUser(new User(idUser));
                return medicoRepository.save(medico);
            }
        } else {
            throw new IllegalArgumentException("Rol de usuario no valido");
        }
    }
}
