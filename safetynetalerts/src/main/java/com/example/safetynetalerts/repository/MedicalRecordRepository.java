package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecordList.add(medicalRecord);
        return medicalRecord;
    }


    public List<MedicalRecord> findBy(String s) {
        return this.findAll().stream()
                .filter((p) -> (p.getLastName() + p.getFirstName()).equals(s))
                .collect(Collectors.toList());
    }

    public List<MedicalRecord> findAll() {
        return this.medicalRecordList;
    }

    public List<MedicalRecord> findByFirstName(String firstName){
        return this.findAll().stream()
                .filter((person -> person.getFirstName().equals(firstName)))
                .collect(Collectors.toList());
    }

    public List<MedicalRecord> findByLastName(String lastName){
        return this.findAll().stream()
                .filter((person -> person.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.findAll().forEach((MedicalRecord medicalRecord) -> {
            if((medicalRecord.getLastName().equals(lastName) && medicalRecord.getFirstName().equals(firstName))){
                this.medicalRecordList.remove(medicalRecord);
            }
        });
    }
   /* public  ArrayList<MedicalRecord> getMedicalRecordList() {
        return this.medicalRecordList;
    }

    public ArrayList<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
        ArrayList<MedicalRecord> addMedicalRecordList = getMedicalRecordList();
        addMedicalRecordList.add(medicalRecord);
        return addMedicalRecordList;
    }*/
}
