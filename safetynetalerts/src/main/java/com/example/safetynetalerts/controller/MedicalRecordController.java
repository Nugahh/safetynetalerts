package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping(value ="/medicalrecord")
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordService.getMedicalRecords();
    }
}
