package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.Patient;
import com.teamcoffee.appdc.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = getPatientById(id);
        existingPatient.setTypeDiabetes(updatedPatient.getTypeDiabetes());
        existingPatient.setDateBirth(updatedPatient.getDateBirth());
        existingPatient.setDirection(updatedPatient.getDirection());
        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        Patient existingPatient = getPatientById(id);
        patientRepository.delete(existingPatient);
    }
}
