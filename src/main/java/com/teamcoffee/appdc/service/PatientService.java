package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.Patient;
import com.teamcoffee.appdc.persistence.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Long id){
        return patientRepository.findById(id);
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public void deleteById(Long id){
        patientRepository.deleteById(id);
    }
}
