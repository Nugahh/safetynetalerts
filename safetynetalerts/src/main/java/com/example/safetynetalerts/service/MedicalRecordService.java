package com.example.safetynetalerts.service;

//import com.example.safetynetalerts.repository.MedicalRecordRepository;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public ArrayList<MedicalRecord> getMedicalRecord() {
        ArrayList<MedicalRecord> getMedicalRecordList = medicalRecordRepository.getMedicalRecordList();
        return getMedicalRecordList;
    }
}
