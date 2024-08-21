package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.Doctor;
import com.teamcoffee.appdc.persistence.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setSpecialty(updatedDoctor.getSpecialty());
        existingDoctor.setLicenseNumber(updatedDoctor.getLicenseNumber());
        return doctorRepository.save(existingDoctor);
    }

    public void deleteDoctor(Long id) {
        Doctor existingDoctor = getDoctorById(id);
        doctorRepository.delete(existingDoctor);
    }
}
