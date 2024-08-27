package com.teamcoffee.appdc.service;

import com.teamcoffee.appdc.persistence.entity.Appointment;
import com.teamcoffee.appdc.persistence.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> findByPatientId(Long patientId){
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> findByDoctorId(Long doctorId){
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public Optional<Appointment> findById(Long id){
        return appointmentRepository.findById(id);
    }

    public Appointment save(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public void deleteById(Long id){
        appointmentRepository.deleteById(id);
    }
}
