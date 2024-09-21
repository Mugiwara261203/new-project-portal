package com.teamcoffee.appdc.persistence.repository;

import com.teamcoffee.appdc.persistence.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
