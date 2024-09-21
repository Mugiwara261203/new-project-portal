package com.teamcoffee.appdc.persistence.repository;

import com.teamcoffee.appdc.persistence.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByPatientId(Long patientId);
}
