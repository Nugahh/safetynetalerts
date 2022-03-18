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

    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    public MedicalRecord getMedicalRecords(String firstName, String lastName){
        if (!firstName.isEmpty() && !lastName.isEmpty()){
            return medicalRecordRepository.findBy(lastName + firstName);
        }
        return null;
    }


   /* public ArrayList<MedicalRecord> getMedicalRecord() {
        ArrayList<MedicalRecord> getMedicalRecordList = medicalRecordRepository.getMedicalRecordList();
        return getMedicalRecordList;
    }*/
}
