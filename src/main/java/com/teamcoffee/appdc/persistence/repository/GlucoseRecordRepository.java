package com.teamcoffee.appdc.persistence.repository;

import com.teamcoffee.appdc.persistence.entity.GlucoseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GlucoseRecordRepository extends JpaRepository<GlucoseRecord, Long> {
    List<GlucoseRecord> findByPatientId(Long patientId);
}
