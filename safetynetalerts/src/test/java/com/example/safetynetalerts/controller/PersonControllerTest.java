package com.example.safetynetalerts.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PersonController.class})
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    private final static Logger logger = LogManager.getLogger(PersonControllerTest.class);

    @Autowired
    private PersonController personController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;

    @Test
    void testGetPersons() throws Exception {
        when(this.personService.getPersons()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person");
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testAddPerson() throws Exception {
        when(this.personService.addPerson(any(Person.class))).thenReturn(new Person());

        Person person = new Person();
        person.setAddress("42 John St");
        person.setCity("Paris");
        person.setEmail("123@gmail.com");
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        String content = (new ObjectMapper()).writeValueAsString(person);
        logger.error(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"firstName\":null,\"lastName\":null,\"address\":null,\"city\":null,\"zip\":null,\"phone\":null,\"email\":null}"));
    }

    @Test
    void testDeletePerson() throws Exception {
        doNothing().when(personService).deletePerson(any(String.class), any(String.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person")
                .param("firstName", "foo")
                .param("lastName", "foo");
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePerson2() throws Exception {
        doNothing().when(personService).deletePerson(any(String.class), any(String.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person")
                .param("firstName", "")
                .param("lastName", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }

    @Test
    void testDeletePerson3() throws Exception {
        doNothing().when(personService).deletePerson(any(String.class), any(String.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person")
                .param("firstName", "foo")
                .param("lastName", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }
}

