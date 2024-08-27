package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.VaccinationHistory;
import com.teamcoffee.appdc.persistence.repository.VaccinationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccinationHistoryService {

    private final VaccinationHistoryRepository vaccinationHistoryRepository;

    public List<VaccinationHistory> findAll(){
        return vaccinationHistoryRepository.findAll();
    }

    public  List<VaccinationHistory> findByPatientId(Long patientId){
        return vaccinationHistoryRepository.findByPatientId(patientId);
    }

    public Optional<VaccinationHistory> findById(Long id){
        return vaccinationHistoryRepository.findById(id);
    }

    public VaccinationHistory save(VaccinationHistory vaccinationHistory){
        return vaccinationHistoryRepository.save(vaccinationHistory);
    }

    public void deleteById(Long id){
        vaccinationHistoryRepository.deleteById(id);
    }
}
