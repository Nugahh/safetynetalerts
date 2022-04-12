package com.example.safetynetalerts.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.service.MedicalRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MedicalRecordController.class})
@ExtendWith(SpringExtension.class)
class MedicalRecordControllerTest {

    @Autowired
    private MedicalRecordController medicalRecordController;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    void testUpdateMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());

        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalrecord")
                .param("firstName", "John")
                .param("lastName", "Doe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"birthdate\":\"03/06/1984\",\"medications\":[],\"allergies\":[]}"));
    }

    @Test
    void testUpdateMedicalRecord2() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());

        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalrecord")
                .param("firstName", "")
                .param("lastName", "Doe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUpdateMedicalRecord3() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());

        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalrecord")
                .param("firstName", "John")
                .param("lastName", "")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testDeleteMedicalRecord() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any(), any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalrecord")
                .param("firstName", "foo")
                .param("lastName", "foo");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteMedicalRecord2() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any(), any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalrecord")
                .param("firstName", "")
                .param("lastName", "foo");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteMedicalRecord3() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any(), any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalrecord")
                .param("firstName", "foo")
                .param("lastName", "");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

