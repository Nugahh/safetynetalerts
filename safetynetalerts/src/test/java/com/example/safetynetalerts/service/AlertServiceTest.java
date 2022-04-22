package com.example.safetynetalerts.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.safetynetalerts.DTO.CommunityEmailDTO;
import com.example.safetynetalerts.DTO.FloodDTO;
import com.example.safetynetalerts.DTO.PersonInfoByAddressDTO;
import com.example.safetynetalerts.DTO.PersonInfoByStationAndCountDTO;
import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.utils.CalculateAge;
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

    @MockBean
    private MapperUtils mapperUtils;

    @Autowired
    private AlertService alertService;

    @MockBean
    private FireStationService fireStationService;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @MockBean
    private PersonService personService;

    @MockBean
    private CalculateAge calculateAge;

   /* @Test
    void toPersonInfoTest() {
        ArrayList<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        personList.add(person);
        when(personService.findPersonByLastName(any())).thenReturn(personList);

        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setFirstName(person.getFirstName());
        when(mapperUtils.toPersonDTO(any(), any())).thenReturn(personInfoDTO);

        assertEquals(0, alertService.toPersonInfo(any(), any()).size());

    }
*/

    @Test
    void toPhoneAlertTest() {
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation());
        when(fireStationService.findFireStationByStation(any())).thenReturn(fireStationList);

        Person person = new Person();
        person.setPhone("1234");
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        when(personService.findByAddress(any())).thenReturn(personList);

        assertEquals(1, alertService.toPhoneAlert(any()).size());
        verify(fireStationService).findFireStationByStation(any());
        verify(personService).findByAddress(any());
    }

    @Test
    void toPhoneAlertPhoneBlankTest() {
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation());
        when(fireStationService.findFireStationByStation(any())).thenReturn(fireStationList);

        Person person = new Person();
        person.setPhone("");
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        when(personService.findByAddress(any())).thenReturn(personList);

        assertEquals(0, alertService.toPhoneAlert(any()).size());
        verify(fireStationService).findFireStationByStation(any());
        verify(personService).findByAddress(any());
    }

    @Test
    void toPhoneAlertPhoneNullTest() {
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(new FireStation());
        when(fireStationService.findFireStationByStation(any())).thenReturn(fireStationList);

        Person person = new Person();
        person.setPhone(null);
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        when(personService.findByAddress(any())).thenReturn(personList);

        assertEquals(0, alertService.toPhoneAlert(any()).size());
        verify(fireStationService).findFireStationByStation(any());
        verify(personService).findByAddress(any());
    }

    /*@Test
    void testToPersonInfoByStation() {
        PersonInfoByStationAndCountDTO personInfo = new PersonInfoByStationAndCountDTO();
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        when(fireStationService.findFireStationByStation(any())).thenReturn(fireStationList);

        when(calculateAge.calculateAge(any())).thenReturn(15);
        when(calculateAge.calculateAge(any())).thenReturn(19);


        PersonInfoByStationAndCountDTO personInfoByStation = alertService.toPersonInfoByStation(any());
        assertEquals(1, personInfoByStation.getAdult().intValue());
        assertEquals(fireStationList, personInfoByStation.getHousehold());
        assertEquals(1, personInfoByStation.getChild().intValue());
        verify(fireStationService).findFireStationByStation((Integer) any());
    }*/
}

