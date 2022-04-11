package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    public List<MedicalRecord> findAll() {
        return this.medicalRecordList;
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecordList.add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecordOld, String firstName, String lastName){

        MedicalRecord medicalRecordNew =  findByFirstNameAndLastName(firstName, lastName);
        medicalRecordNew.setBirthdate(medicalRecordOld.getBirthdate());
        medicalRecordNew.setMedications(medicalRecordOld.getMedications());
        medicalRecordNew.setAllergies(medicalRecordOld.getAllergies());

        return medicalRecordList.set(medicalRecordList.indexOf(findByFirstNameAndLastName(firstName, lastName)), medicalRecordNew);
    }

    public MedicalRecord findByFirstNameAndLastName(String firstName, String lastName) {
        return this.medicalRecordList.stream()
                .filter(medicalRecord -> (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName))).findAny().orElseThrow();
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.medicalRecordList.removeIf(medicalRecord ->
                medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName));
    }
}
