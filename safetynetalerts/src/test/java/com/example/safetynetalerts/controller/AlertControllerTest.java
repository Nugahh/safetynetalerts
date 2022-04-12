package com.example.safetynetalerts.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.safetynetalerts.DTO.PersonInfoByStationAndCountDTO;
import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.service.AlertService;
import com.example.safetynetalerts.service.FireStationService;
import com.example.safetynetalerts.service.JSONReaderService;
import com.example.safetynetalerts.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AlertControllerTest {

    @Autowired
    private AlertController alertController;

    @MockBean
    private AlertService alertService;

    @MockBean
    private FireStationService fireStationService;

    @MockBean
    private PersonService personService;

    @Test
    void getPersonsByFirstNameAndLastNameTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/personInfo")
                .param("firstName", "John")
                .param("lastName", "Doe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getPersonWithFirstNameBlankTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/personInfo")
                .param("firstName", "")
                .param("lastName", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getPersonWithLastNameBlankTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/personInfo")
                .param("firstName", "foo")
                .param("lastName", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getPersonWithFirstNameAndLastNameBlankTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/personInfo")
                .param("firstName", "")
                .param("lastName", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getEmailByCityTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/communityEmail").param("city", "Culver");
        MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getEmailWithoutCityTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/communityEmail").param("city", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getEmailIsEmptyTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/communityEmail").param("city", " ");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetPersonsByStation() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flood/stations").param("stations", "1, 2");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetPersonsByStation2() throws Exception {
        ArrayList<FireStation> fireStationList = new ArrayList<>();
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);
        FireStation fireStation2 = new FireStation();
        fireStation2.setAddress("14 Culver St");
        fireStation2.setStation(2);
        fireStationList.add(fireStation);
        fireStationList.add(fireStation2);

        when(fireStationService.findFireStationByStations(any())).thenReturn(fireStationList);
        when(alertService.toFlood(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flood/stations").param("stations", "1, 2");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetPersonByAddress() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fire").param("address", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetPersonByAddress2() throws Exception {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person());
        when(personService.findByAddress(any())).thenReturn(personList);
        when(alertService.toPersonInfoByAddress(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fire").param("address", "42 Main St");
        MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPersonByAddress3() throws Exception {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person());
        when(this.personService.findByAddress(any())).thenReturn(personList);
        when(this.alertService.toPersonInfoByAddress(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fire").param("address", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetPhoneByStation() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/phoneAlert")
                .param("firestation", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetPhoneByStation2() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);
        when(this.fireStationService.findFireStationByStation(any())).thenReturn(fireStationList);
        when(this.alertService.toPhoneAlert(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/phoneAlert").param("firestation", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetChildByAddress() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert").param("address", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetChildByAddress2() throws Exception {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person());
        when(personService.findByAddress(any())).thenReturn(personList);
        when(alertService.toChildAlertByAddress(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert").param("address", "foo");
        MockMvcBuilders.standaloneSetup(this.alertController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetChildByAddress3() throws Exception {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person());
        when(this.personService.findByAddress(any())).thenReturn(personList);
        when(this.alertService.toChildAlertByAddress(any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert").param("address", " ");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetPersonByStation() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/firestations");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("stationNumber", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetPersonByStation2() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        ArrayList<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);
        when(fireStationService.findFireStationByStation(any())).thenReturn(fireStationList);

        PersonInfoByStationAndCountDTO personInfoByStationAndCountDTO = new PersonInfoByStationAndCountDTO();
        personInfoByStationAndCountDTO.setAdult(1);
        personInfoByStationAndCountDTO.setChild(1);
        personInfoByStationAndCountDTO.setHousehold(new ArrayList<>());
        when(alertService.toPersonInfoByStation(any())).thenReturn(personInfoByStationAndCountDTO);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/firestations");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("stationNumber", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(alertController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"household\":[],\"child\":1,\"adult\":1}"));
    }
}
