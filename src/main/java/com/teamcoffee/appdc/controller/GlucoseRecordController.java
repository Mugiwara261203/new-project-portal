package com.teamcoffee.appdc.controller;

import com.teamcoffee.appdc.persistence.entity.GlucoseRecord;
import com.teamcoffee.appdc.service.GlucoseRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/glucose-records")
@RequiredArgsConstructor
public class GlucoseRecordController {

    private final GlucoseRecordService glucoseRecordService;

    @GetMapping
    public List<GlucoseRecord> getAllGlucoseRecords(){
        return glucoseRecordService.findAll();
    }

    @GetMapping("/patient/{patientId}")
    public List<GlucoseRecord> getGlucoseRecordsByPatientId(@PathVariable Long patientId){
        return glucoseRecordService.findByPatientId(patientId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlucoseRecord> getGlucoseRecordById(@PathVariable Long id){
        return glucoseRecordService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public GlucoseRecord createGlucoseRecord(@RequestBody GlucoseRecord glucoseRecord){
        return glucoseRecordService.save(glucoseRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlucoseRecord> updateGlucoseRecord(@PathVariable Long id, @RequestBody GlucoseRecord glucoseRecordDetails){
        return glucoseRecordService.findById(id)
                .map(glucoseRecord -> {
                    glucoseRecord.setGlucoseLevel(glucoseRecordDetails.getGlucoseLevel());
                    glucoseRecord.setMeasurementDate(glucoseRecordDetails.getMeasurementDate());
                    glucoseRecord.setNotes(glucoseRecordDetails.getNotes());
                    return ResponseEntity.ok(glucoseRecordService.save(glucoseRecord));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGlucoseRecord(@PathVariable Long id){
        return glucoseRecordService.findById(id)
                .map(glucoseRecord -> {
                    glucoseRecordService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
