package com.teamcoffee.appdc.persistence.repository;

import com.teamcoffee.appdc.persistence.entity.AdmissionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionHistoryRepository extends JpaRepository<AdmissionHistory, Long> {
    List<AdmissionHistory> findByPatientId(Long patientId);
}
