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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MedicalRecordControllerTest {

    @Autowired
    private MedicalRecordController medicalRecordController;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    void updateMedicalRecordTest() throws Exception {
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
                .andExpect(MockMvcResultMatchers.content().string("{\"" +
                        "firstName\":\"John\"," +
                        "\"lastName\":\"Doe\"," +
                        "\"birthdate\":\"03/06/1984\"," +
                        "\"medications\":[]," +
                        "\"allergies\":[]" +
                        "}"));
    }

    @Test
    void updateMedicalRecordFirstNameBlankTest() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());

        when(medicalRecordService.updateMedicalRecord(any(), any(), any())).thenReturn(medicalRecord);

        String content = (new ObjectMapper()).writeValueAsString(medicalRecord);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicalrecord")
                .param("firstName", " ")
                .param("lastName", "Doe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateMedicalRecordLastNameBlankTest() throws Exception {
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
                .param("lastName", " ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateMedicalRecordFirstNameEmptyTest() throws Exception {
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
    void updateMedicalRecordLastNameEmptyTest() throws Exception {
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
    void deleteMedicalRecordTest() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any(), any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalrecord")
                .param("firstName", "John")
                .param("lastName", "Doe");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteMedicalRecordFirstNameBlankTest() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any(), any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalrecord")
                .param("firstName", " ")
                .param("lastName", "Doe");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteMedicalRecordLastNameBlankTest() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any(), any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalrecord")
                .param("firstName", "John")
                .param("lastName", " ");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteMedicalRecordFirstNameEmptyTest() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any(), any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalrecord")
                .param("firstName", "")
                .param("lastName", "Doe");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteMedicalRecordLastNameEmptyTest() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any(), any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicalrecord")
                .param("firstName", "John")
                .param("lastName", "");
        MockMvcBuilders.standaloneSetup(medicalRecordController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

