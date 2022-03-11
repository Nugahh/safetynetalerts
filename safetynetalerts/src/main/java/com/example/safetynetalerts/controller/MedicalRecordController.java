package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    /*@DeleteMapping(value ="/medicalrecord")
    public MedicalRecord deleteMedicalRecords(String firstName, String lastName) {
        return medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }*/
}
