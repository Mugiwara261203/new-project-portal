package com.teamcoffee.appdc.controller;

import com.teamcoffee.appdc.persistence.entity.Treatment;
import com.teamcoffee.appdc.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;

    @GetMapping
    public List<Treatment> getAllTreatments(){
        return treatmentService.findAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<Treatment> getTreatmentsByPatientId(@PathVariable Long patientId){
        return treatmentService.findByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id){
        return treatmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
