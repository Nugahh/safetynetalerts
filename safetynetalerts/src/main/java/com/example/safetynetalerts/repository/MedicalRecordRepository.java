package com.example.safetynetalerts.repository;
import com.example.safetynetalerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecordList.add(medicalRecord);
        return medicalRecord;
    }

    public MedicalRecord findByLastNameAndFirstName(String s) {
        return this.medicalRecordList.stream()
                .filter((p) -> (p.getLastName() + p.getFirstName()).equals(s)).findAny().orElseThrow();
    }

    public List<MedicalRecord> findAll() {
        return this.medicalRecordList;
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.medicalRecordList.forEach((MedicalRecord medicalRecord) -> {
            if((medicalRecord.getLastName().equals(lastName) && medicalRecord.getFirstName().equals(firstName))){
                this.medicalRecordList.remove(medicalRecord);
            }
        });
    }
}
