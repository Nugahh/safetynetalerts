package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping(value ="/medicalrecord")
    public ArrayList<MedicalRecord> getMedicalRecord(){
        ArrayList<MedicalRecord>medicalRecordList = medicalRecordService.getMedicalRecord();
        return medicalRecordList;
    }
}