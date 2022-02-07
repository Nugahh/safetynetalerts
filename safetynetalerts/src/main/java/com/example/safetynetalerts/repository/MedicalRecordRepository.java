package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;

@Repository
public class MedicalRecordRepository extends JSONReaderRepository  {

    public MedicalRecordRepository() throws IOException {
    }

    public ArrayList<MedicalRecord> getMedicalRecordList(){
        return this.readMedicalRecordsList();
    }
}
