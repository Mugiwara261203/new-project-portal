package com.teamcoffee.appdc.controller;

import com.teamcoffee.appdc.persistence.entity.Patient;
import com.teamcoffee.appdc.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientByid(@PathVariable Long id){
        return patientService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.save(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails){
        return patientService.findById(id)
                .map(patient -> {
                    patient.setDiabetesType(patientDetails.getDiabetesType());
                    patient.setGender(patientDetails.getGender());
                    patient.setBirthDate(patientDetails.getBirthDate());
                    return ResponseEntity.ok(patientService.save(patient));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long id){
        return patientService.findById(id)
                .map(patient -> {
                    patientService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
