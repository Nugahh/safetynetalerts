package com.example.safetynetalerts.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.service.FireStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FireStationController.class})
@ExtendWith(SpringExtension.class)
class FireStationControllerTest {

    @Autowired
    private FireStationController fireStationController;

    @MockBean
    private FireStationService fireStationService;

    @Test
    void deleteFireStationAddressBlankTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", " ");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteFireStationAddressEmptyAndStationNullTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", (String) null);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteFireStationAddressEmptyTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteFireStationStationNullTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", (String) null);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void deleteFireStationStationTest() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.delete("/firestation")
                .param("address", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("station", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateFireStationTest() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        when(fireStationService.updateFireStation(any(), any())).thenReturn(fireStation);

        String content = (new ObjectMapper()).writeValueAsString(fireStation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/firestation")
                .param("address", "42 Main St")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"address\":\"42 Main St\",\"station\":1}"));
    }

    @Test
    void updateFireStationAddressEmptyTest() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        when(fireStationService.updateFireStation(any(), any())).thenReturn(fireStation);

        String content = (new ObjectMapper()).writeValueAsString(fireStation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/firestation")
                .param("address", "")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateFireStationAddressBlankTest() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("42 Main St");
        fireStation.setStation(1);

        when(fireStationService.updateFireStation(any(), any())).thenReturn(fireStation);

        String content = (new ObjectMapper()).writeValueAsString(fireStation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/firestation")
                .param("address", " ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.fireStationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

