package com.teamcoffee.appdc.persistence.repository;

import com.teamcoffee.appdc.persistence.entity.VaccinationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory, Long> {
    List<VaccinationHistory> findByPatientId(Long patientId);
}
