package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.Doctor;
import com.teamcoffee.appdc.persistence.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findById(Long id){
        return doctorRepository.findById(id);
    }

    public Doctor save(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public void deleteById(Long id){
        doctorRepository.deleteById(id);
    }
}
