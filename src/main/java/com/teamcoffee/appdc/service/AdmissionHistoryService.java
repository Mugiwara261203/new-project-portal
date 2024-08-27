package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.AdmissionHistory;
import com.teamcoffee.appdc.persistence.repository.AdmissionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmissionHistoryService {

    private final AdmissionHistoryRepository admissionHistoryRepository;

    public List<AdmissionHistory> findAll(){
        return admissionHistoryRepository.findAll();
    }

    public List<AdmissionHistory> findByPatientId(Long patientId){
        return admissionHistoryRepository.findByPatientId(patientId);
    }

    public Optional<AdmissionHistory> findById(Long id){
        return admissionHistoryRepository.findById(id);
    }

    public AdmissionHistory save(AdmissionHistory admissionHistory){
        return admissionHistoryRepository.save(admissionHistory);
    }

    public void deleteById(Long id){
        admissionHistoryRepository.deleteById(id);
    }
}
