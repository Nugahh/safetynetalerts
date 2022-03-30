package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping(value = "/medicalrecord")
    public List<MedicalRecord> getMedicalRecord() {
        return medicalRecordService.getMedicalRecords();
    }

    @PostMapping(value = "/medicalrecord")
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }

    @PutMapping(value = "/medicalrecord")
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }
    @DeleteMapping(value = "/medicalrecord")
    public void deleteMedicalRecord(@RequestBody String firstName, String lastName) {
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}
