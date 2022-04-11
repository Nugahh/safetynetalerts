package com.example.safetynetalerts.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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

    @Autowired
    private AlertService alertService;

    @MockBean
    private FireStationService fireStationService;

    @Autowired
    private PersonService personService;

  /*  @BeforeEach
    void setUp() {
        jsonReaderService = new JSONReaderService(objectMapper);

    }*/
    @Test
    void getPersonsByFirstNameAndLastNameTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/personInfo")
                .param("firstName", "foo")
                .param("lastName", "foo");
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
}
