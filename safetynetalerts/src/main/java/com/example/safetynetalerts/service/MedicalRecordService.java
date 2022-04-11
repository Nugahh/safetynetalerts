package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord){
        return medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName){
        return medicalRecordRepository.updateMedicalRecord(medicalRecord, firstName, lastName);
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    public MedicalRecord findMedicalRecordsByFirstNameAndLastName(String firstName, String lastName){
            return medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
