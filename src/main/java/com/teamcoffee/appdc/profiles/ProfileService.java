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

    public Paciente updatePacienteProfile(Paciente profile, Integer idUser){
        return pacienteRepository.findByUserId(idUser)
                .map(existing -> updateExistingPaciente(existing, profile))
                .orElseGet(() -> saveNewPaciente(profile, idUser));
    }

    public Medico updateMedicoProfile(Medico profile, Integer idUser){
        return medicoRepository.findByUserId(idUser)
                .map(existing -> updateExistingMedico(existing, profile))
                .orElseGet(() -> saveNewMedico(profile, idUser));
    }

    private Paciente updateExistingPaciente(Paciente existing, Paciente profile){
        existing.setUsername(profile.getUsername());
        existing.setLastname(profile.getLastname());
        existing.setDateBirth(profile.getDateBirth());
        existing.setAddress(profile.getAddress());
        existing.setPhoneNumber(profile.getPhoneNumber());
        existing.setTypeDiabetes(profile.getTypeDiabetes());
        return pacienteRepository.save(existing);
    }

    private Paciente saveNewPaciente(Paciente profile, Integer idUser){
        profile.setUser(new User(idUser));
        return pacienteRepository.save(profile);
    }

    private Medico updateExistingMedico(Medico existing, Medico profile){
        existing.setUsername(profile.getUsername());
        existing.setLastname(profile.getLastname());
        existing.setSpecialization(profile.getSpecialization());
        existing.setPhoneNumber(profile.getPhoneNumber());
        return medicoRepository.save(existing);
    }

    private Medico saveNewMedico(Medico profile, Integer idUser){
        profile.setUser(new User(idUser));
        return medicoRepository.save(profile);
    }
}
