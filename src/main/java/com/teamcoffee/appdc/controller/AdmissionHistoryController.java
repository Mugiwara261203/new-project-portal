package com.teamcoffee.appdc.controller;

import com.teamcoffee.appdc.persistence.entity.AdmissionHistory;
import com.teamcoffee.appdc.service.AdmissionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admission-histories")
@RequiredArgsConstructor
public class AdmissionHistoryController {
    private final AdmissionHistoryService admissionHistoryService;

    @GetMapping
    public List<AdmissionHistory> getAllAdmissionHistories(){
        return admissionHistoryService.findAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<AdmissionHistory> getAdmissionHistoriesByPatientId(@PathVariable Long patientId){
        return admissionHistoryService.findByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionHistory> getAdmissionHistoryById(@PathVariable Long id){
        return admissionHistoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AdmissionHistory createAdmissionHistory(@RequestBody AdmissionHistory admissionHistory){
        return admissionHistoryService.save(admissionHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionHistory> updateAdmissionHistory(@PathVariable Long id, @RequestBody AdmissionHistory admissionHistoryDetails){
        return admissionHistoryService.findById(id)
                .map(admissionHistory -> {
                    admissionHistory.setAdmissionType(admissionHistoryDetails.getAdmissionType());
                    admissionHistory.setAdmissionDate(admissionHistoryDetails.getAdmissionDate());
                    admissionHistory.setNotes(admissionHistoryDetails.getNotes());
                    return ResponseEntity.ok(admissionHistoryService.save(admissionHistory));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdmissionHistory(@PathVariable Long id){
        return admissionHistoryService.findById(id)
                .map(admissionHistory -> {
                    admissionHistoryService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
