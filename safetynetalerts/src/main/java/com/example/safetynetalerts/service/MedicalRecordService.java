package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<MedicalRecord> getMedicalRecords(Optional<String> firstName, Optional<String> lastName){
        if (firstName.isPresent() && lastName.isPresent()){
            return medicalRecordRepository.findBy(lastName.get() + firstName.get());
        }
        if (firstName.isPresent()){
            return medicalRecordRepository.findByFirstName(firstName.get());
        }
        if (lastName.isPresent()){
            return medicalRecordRepository.findByLastName(lastName.get());
        }
        return medicalRecordRepository.findAll();
    }


   /* public ArrayList<MedicalRecord> getMedicalRecord() {
        ArrayList<MedicalRecord> getMedicalRecordList = medicalRecordRepository.getMedicalRecordList();
        return getMedicalRecordList;
    }*/
}
