package com.example.safetynetalerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.safetynetalerts.DTO.FloodDTO;
import com.example.safetynetalerts.DTO.PersonInfoByAddressDTO;
import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ContextConfiguration("/Data.json")
@ContextConfiguration(classes = {AlertService.class})
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @Autowired
    private MapperUtils mapperUtils;

    @Autowired
    private AlertService alertService;

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private PersonService personService;

    @Test
    void testToPersonInfoByAddress() {
        when(personService.findByAddress(any())).thenReturn(new ArrayList<>());
        assertTrue(alertService.toPersonInfoByAddress("42 Main St").isEmpty());
        verify(personService).findByAddress(any());
    }

    @Test
    void testToPersonInfoByAddress2() {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person());
        when(this.personService.findByAddress(any())).thenReturn(personList);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        when(this.medicalRecordService.findMedicalRecordsByFirstNameAndLastName((String) any(), (String) any()))
                .thenReturn(medicalRecord);

        PersonInfoByAddressDTO personInfoByAddressDTO = new PersonInfoByAddressDTO();
        personInfoByAddressDTO.setAllergies(new ArrayList<>());
        personInfoByAddressDTO.setBirthdate(1);
        personInfoByAddressDTO.setLastName("Doe");
        personInfoByAddressDTO.setMedications(new ArrayList<>());
        personInfoByAddressDTO.setPhone("4105551212");
        personInfoByAddressDTO.setStation(1);
        when(this.mapperUtils.toPersonInfoByAddressDTO((Person) any(), (MedicalRecord) any(), (FireStation) any()))
                .thenReturn(personInfoByAddressDTO);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);
        when(this.fireStationService.findFireStationByAddressList((String) any())).thenReturn(fireStation);
        assertEquals(1, this.alertService.toPersonInfoByAddress("42 Main St").size());
        verify(this.personService).findByAddress((String) any());
        verify(this.medicalRecordService).findMedicalRecordsByFirstNameAndLastName((String) any(), (String) any());
        verify(this.mapperUtils).toPersonInfoByAddressDTO((Person) any(), (MedicalRecord) any(), (FireStation) any());
        verify(this.fireStationService).findFireStationByAddressList((String) any());
    }

    @Test
    void testToPersonInfoByAddress3() {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person());
        personList.add(new Person());
        when(this.personService.findByAddress((String) any())).thenReturn(personList);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        when(this.medicalRecordService.findMedicalRecordsByFirstNameAndLastName((String) any(), (String) any()))
                .thenReturn(medicalRecord);

        PersonInfoByAddressDTO personInfoByAddressDTO = new PersonInfoByAddressDTO();
        personInfoByAddressDTO.setAllergies(new ArrayList<>());
        personInfoByAddressDTO.setBirthdate(1);
        personInfoByAddressDTO.setLastName("Doe");
        personInfoByAddressDTO.setMedications(new ArrayList<>());
        personInfoByAddressDTO.setPhone("4105551212");
        personInfoByAddressDTO.setStation(1);
        when(this.mapperUtils.toPersonInfoByAddressDTO((Person) any(), (MedicalRecord) any(), (FireStation) any()))
                .thenReturn(personInfoByAddressDTO);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);
        when(this.fireStationService.findFireStationByAddressList((String) any())).thenReturn(fireStation);
        assertEquals(2, this.alertService.toPersonInfoByAddress("42 Main St").size());
        verify(this.personService).findByAddress((String) any());
        verify(this.medicalRecordService, atLeast(1)).findMedicalRecordsByFirstNameAndLastName((String) any(),
                (String) any());
        verify(this.mapperUtils, atLeast(1)).toPersonInfoByAddressDTO((Person) any(), (MedicalRecord) any(),
                (FireStation) any());
        verify(this.fireStationService, atLeast(1)).findFireStationByAddressList((String) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testToPersonInfoByAddress4() {

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(null);
        when(this.personService.findByAddress((String) any())).thenReturn(personList);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAllergies(new ArrayList<>());
        medicalRecord.setBirthdate("2020-03-01");
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setMedications(new ArrayList<>());
        when(this.medicalRecordService.findMedicalRecordsByFirstNameAndLastName((String) any(), (String) any()))
                .thenReturn(medicalRecord);

        PersonInfoByAddressDTO personInfoByAddressDTO = new PersonInfoByAddressDTO();
        personInfoByAddressDTO.setAllergies(new ArrayList<>());
        personInfoByAddressDTO.setBirthdate(1);
        personInfoByAddressDTO.setLastName("Doe");
        personInfoByAddressDTO.setMedications(new ArrayList<>());
        personInfoByAddressDTO.setPhone("4105551212");
        personInfoByAddressDTO.setStation(1);
        when(this.mapperUtils.toPersonInfoByAddressDTO((Person) any(), (MedicalRecord) any(), (FireStation) any()))
                .thenReturn(personInfoByAddressDTO);

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);
        when(this.fireStationService.findFireStationByAddressList((String) any())).thenReturn(fireStation);
        this.alertService.toPersonInfoByAddress("42 Main St");
    }

}

