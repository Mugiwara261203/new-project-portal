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
    private final TwilioService twilioService;

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
        Appointment savedAppointment = appointmentRepository.save(appointment);
        sendAppointmentNotification(savedAppointment);
        return savedAppointment;
    }

    public void deleteById(Long id){
        appointmentRepository.deleteById(id);
    }

    private void sendAppointmentNotification(Appointment appointment) {
        String message = String.format("Su cita fue agendada para %s", appointment.getAppointmentDate().toString());
        try {
            twilioService.sendSms(appointment.getPatient().getPhone(), message);
        } catch (Exception e) {
            System.err.println("No se pudo enviar la notificacion de la cita: " + e.getMessage());
        }
    }
}
