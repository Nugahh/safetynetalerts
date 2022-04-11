package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {

    private final static Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping(value = "/medicalrecord")
    public ResponseEntity<MedicalRecord> getMedicalRecord() {
        logger.info("List of medical records generated");
        return new ResponseEntity(medicalRecordService.getMedicalRecords(), HttpStatus.OK);
    }

    @PostMapping(value = "/medicalrecord")
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info(medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + "'s medical record has been created");
        return new ResponseEntity(medicalRecordService.addMedicalRecord(medicalRecord), HttpStatus.CREATED);
    }

    @PutMapping(value = "/medicalrecord")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, @RequestParam String firstName, @RequestParam String lastName){
        if (firstName.isBlank() || lastName.isBlank() || firstName.isEmpty() || lastName.isEmpty()){
            logger.error("Medical record not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else{
            logger.info(firstName + " " + lastName + "'s medical record has been updated");
            return new ResponseEntity(medicalRecordService.updateMedicalRecord(medicalRecord, firstName, lastName), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/medicalrecord")
    public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank() || firstName.isEmpty() || lastName.isEmpty()){
            logger.error("Firstname or lastname blank");
        } else{
            logger.info(firstName + " " + lastName + "'s medical record has been deleted");
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
        }
    }
}
