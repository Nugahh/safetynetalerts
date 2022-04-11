package com.example.safetynetalerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.safetynetalerts.DTO.FloodDTO;
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
    void testToFlood() {
        when(this.fireStationService.findFireStationByStations((List<Integer>) any())).thenReturn(new ArrayList<>());
        assertTrue(this.alertService.toFlood(new ArrayList<>()).isEmpty());
        verify(this.fireStationService).findFireStationByStations((List<Integer>) any());
    }

    @Test
    void testToFlood2() {
        when(this.personService.findByAddress(any())).thenReturn(new ArrayList<>());

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);
        when(this.fireStationService.findFireStationByStations((List<Integer>) any())).thenReturn(fireStationList);
        ArrayList<Integer> integerList = new ArrayList<>();
        List<FloodDTO> actualToFloodResult = this.alertService.toFlood(integerList);
        assertEquals(1, actualToFloodResult.size());
        FloodDTO getResult = actualToFloodResult.get(0);
        assertEquals("42 Main St", getResult.getAddress());
        assertEquals(integerList, getResult.getPersons());
        verify(this.personService).findByAddress((String) any());
        verify(this.fireStationService).findFireStationByStations((List<Integer>) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testToFlood3() {
        // TODO: Complete this test.
        ArrayList<Person> personList = new ArrayList<>();
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

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);
        when(this.fireStationService.findFireStationByStations((List<Integer>) any())).thenReturn(fireStationList);
        this.alertService.toFlood(new ArrayList<>());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testToFlood4() {
        // TODO: Complete this test.

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

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);
        when(this.fireStationService.findFireStationByStations((List<Integer>) any())).thenReturn(fireStationList);
        this.alertService.toFlood(new ArrayList<>());
    }

    @Test
    void testToFlood5() {
        when(this.personService.findByAddress((String) any())).thenReturn(new ArrayList<>());

        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        FireStation fireStation1 = new FireStation();
        fireStation1.setAddress("42 Main St");
        fireStation1.setStation(1);

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation1);
        fireStationList.add(fireStation);
        when(this.fireStationService.findFireStationByStations((List<Integer>) any())).thenReturn(fireStationList);
        ArrayList<Integer> integerList = new ArrayList<>();
        List<FloodDTO> actualToFloodResult = this.alertService.toFlood(integerList);
        assertEquals(2, actualToFloodResult.size());
        FloodDTO getResult = actualToFloodResult.get(0);
        assertEquals(integerList, getResult.getPersons());
        FloodDTO getResult1 = actualToFloodResult.get(1);
        assertEquals(integerList, getResult1.getPersons());
        assertEquals("42 Main St", getResult1.getAddress());
        assertEquals("42 Main St", getResult.getAddress());
        verify(this.personService, atLeast(1)).findByAddress((String) any());
        verify(this.fireStationService).findFireStationByStations((List<Integer>) any());
    }
}

