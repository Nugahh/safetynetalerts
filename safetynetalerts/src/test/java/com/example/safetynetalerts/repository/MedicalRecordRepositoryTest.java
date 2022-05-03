package com.example.safetynetalerts.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.example.safetynetalerts.model.MedicalRecord;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

class MedicalRecordRepositoryTest {

    private final MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

    @Test
    @DisplayName("Given old MedicalRecord and new MedicalRecord, when updateMedicalRecord, then new MedicalRecord replace old MedicalRecord")
    void updateMedicalRecordTest() {
        // Given
        MedicalRecord medicalRecordOld = new MedicalRecord();
        medicalRecordOld.setAllergies(new ArrayList<>());
        medicalRecordOld.setBirthdate("2020-03-01");
        medicalRecordOld.setFirstName("Jane");
        medicalRecordOld.setLastName("Doe");
        medicalRecordOld.setMedications(new ArrayList<>());

        medicalRecordRepository.addMedicalRecord(medicalRecordOld);

        MedicalRecord medicalRecordNew = new MedicalRecord();
        medicalRecordNew.setAllergies(new ArrayList<>());
        medicalRecordNew.setBirthdate("1994-03-01");
        medicalRecordNew.setFirstName("Jane");
        medicalRecordNew.setLastName("Doe");
        medicalRecordNew.setMedications(new ArrayList<>());

        // Then
        assertSame(medicalRecordOld, medicalRecordRepository.updateMedicalRecord(medicalRecordNew, "Jane",
                "Doe"));
    }

    @Test
    @DisplayName("Given new MedicalRecord, when find by firstname and lastname, then return medical record")
    void findByFirstNameAndLastNameTest() {
        // Given
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        // Then
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        assertSame(medicalRecord, medicalRecordRepository.findByFirstNameAndLastName("John", "Doe"));
    }

    @Test
    @DisplayName("Given new MedicalRecord, when delete by firstname and lastname, then medical record deleted")
    void deleteByFirstNameAndLastNameTest() {
        // Given
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        // When
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        medicalRecordRepository.deleteByFirstNameAndLastName("John", "Doe");

        // Then
        assertTrue(medicalRecordRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Given new MedicalRecord, when delete by lastname only, then medical record remains")
    void deleteByFirstNameAndLastNamFirstNameBlankTest() {
        // Given
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        // When
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        medicalRecordRepository.deleteByFirstNameAndLastName("", "Doe");

        // Then
        assertFalse(medicalRecordRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Given new MedicalRecord, when delete lastname only, then medical record remains")
    void deleteByFirstNameAndLastNameLastNameBlankTest() {
        // Given
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());

        // When
        medicalRecordRepository.addMedicalRecord(medicalRecord);
        medicalRecordRepository.deleteByFirstNameAndLastName("John", "");

        // Then
        assertFalse(medicalRecordRepository.findAll().isEmpty());
    }
}
