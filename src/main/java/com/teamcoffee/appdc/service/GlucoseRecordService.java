package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.GlucoseRecord;
import com.teamcoffee.appdc.persistence.repository.GlucoseRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GlucoseRecordService {

    private final GlucoseRecordRepository glucoseRecordRepository;

    public List<GlucoseRecord> findAll(){
        return glucoseRecordRepository.findAll();
    }

    public List<GlucoseRecord> findByPatientId(Long patientId){
        return glucoseRecordRepository.findByPatientId(patientId);
    }

    public Optional<GlucoseRecord> findById(Long id){
        return glucoseRecordRepository.findById(id);
    }

    public GlucoseRecord save(GlucoseRecord glucoseRecord){
        return glucoseRecordRepository.save(glucoseRecord);
    }

    public void deleteById(Long id){
        glucoseRecordRepository.deleteById(id);
    }

}
