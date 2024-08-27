package com.teamcoffee.appdc.controller;

import com.teamcoffee.appdc.persistence.entity.Appointment;
import com.teamcoffee.appdc.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments(){
        return appointmentService.findAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long patientId){
        return appointmentService.findByPatientId(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable Long doctorId){
        return appointmentService.findByDoctorId(doctorId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id){
        return appointmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentService.save(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails){
        return appointmentService.findById(id)
                .map(appointment -> {
                    appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
                    appointment.setStatus(appointmentDetails.getStatus());
                    appointment.setNotes(appointmentDetails.getNotes());
                    return ResponseEntity.ok(appointmentService.save(appointment));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable Long id){
        return appointmentService.findById(id)
                .map(appointment -> {
                    appointmentService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
