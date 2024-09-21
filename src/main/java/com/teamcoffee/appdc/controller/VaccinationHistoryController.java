package com.teamcoffee.appdc.controller;

import com.teamcoffee.appdc.persistence.entity.VaccinationHistory;
import com.teamcoffee.appdc.service.VaccinationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccination-histories")
@RequiredArgsConstructor
public class VaccinationHistoryController {

    private final VaccinationHistoryService vaccinationHistoryService;

    @GetMapping
    public List<VaccinationHistory> getAllVaccinationHistories(){
        return vaccinationHistoryService.findAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<VaccinationHistory> getAllVaccinationHistoriesByPatientId(@PathVariable Long patientId){
        return vaccinationHistoryService.findByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinationHistory> getVaccinationById(@PathVariable Long id){
        return vaccinationHistoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public VaccinationHistory createVaccinationHistory(@RequestBody VaccinationHistory vaccinationHistory){
        return vaccinationHistoryService.save(vaccinationHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinationHistory> updateVaccinationHistory(@PathVariable Long id, @RequestBody VaccinationHistory vaccinationHistoryDetails){
        return vaccinationHistoryService.findById(id)
                .map(vaccinationHistory -> {
                    vaccinationHistory.setVaccineName(vaccinationHistoryDetails.getVaccineName());
                    vaccinationHistory.setVaccinationDate(vaccinationHistoryDetails.getVaccinationDate());
                    vaccinationHistory.setNotes(vaccinationHistoryDetails.getNotes());
                    return ResponseEntity.ok(vaccinationHistoryService.save(vaccinationHistory));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVaccinationHistory(@PathVariable Long id){
        return vaccinationHistoryService.findById(id)
                .map(vaccinationHistory -> {
                    vaccinationHistoryService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
