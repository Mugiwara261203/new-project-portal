package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.Treatment;
import lombok.RequiredArgsConstructor;
import com.teamcoffee.appdc.persistence.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    public List<Treatment> findAll(){
        return treatmentRepository.findAll();
    }

    public List<Treatment> findByPatientId(Long patientId){
        return treatmentRepository.findByPatientId(patientId);
    }

    public Optional<Treatment> findById(Long id){
        return treatmentRepository.findById(id);
    }

    public Treatment save(Treatment treatment){
        return treatmentRepository.save(treatment);
    }

    public void deleteById(Long id){
        treatmentRepository.deleteById(id);
    }

}
