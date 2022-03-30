package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
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

    public void deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    public MedicalRecord getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName){
        if (!firstName.isEmpty() && !lastName.isEmpty()){
            return medicalRecordRepository.findByLastNameAndFirstName(lastName + firstName);
        }
        return null;
    }


//    public MedicalRecord getMedicalRecordsByStation(String station)

   /* public ArrayList<MedicalRecord> getMedicalRecord() {
        ArrayList<MedicalRecord> getMedicalRecordList = medicalRecordRepository.getMedicalRecordList();
        return getMedicalRecordList;
    }*/
}
