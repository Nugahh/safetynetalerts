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
import java.util.List;

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

    @MockBean
    private PersonService personService;

    @Test
    void getPersonTest() throws Exception {
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
    void addPersonTest() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("42 John St");
        person.setCity("Paris");
        person.setZip("21654");
        person.setPhone("4105551212");
        person.setEmail("123@gmail.com");

        // When
        when(this.personService.addPerson(any(Person.class))).thenReturn(person);

        // Then
        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"" +
                                "firstName\":\"John\"," +
                                "\"lastName\":\"Doe\"," +
                                "\"address\":\"42 John St\"," +
                                "\"city\":\"Paris\"," +
                                "\"zip\":\"21654\"," +
                                "\"phone\":\"4105551212\"," +
                                "\"email\":\"123@gmail.com\"" +
                                "}"));
    }

    @Test
    void updatePersonTest() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("32 John St");
        person.setCity("New York");
        person.setZip("21654");
        person.setPhone("4105551212");
        person.setEmail("123@gmail.com");

        // When
        when(this.personService.updatePerson(any(), any(), any())).thenReturn(person);

        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/person")
                .param("firstName", "John")
                .param("lastName", "Doe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"" +
                                "firstName\":\"John\"," +
                                "\"lastName\":\"Doe\"," +
                                "\"address\":\"32 John St\"," +
                                "\"city\":\"New York\"," +
                                "\"zip\":\"21654\"," +
                                "\"phone\":\"4105551212\"," +
                                "\"email\":\"123@gmail.com\"" +
                                "}"));
    }

    @Test
    void updatePersonFirstNameBlankTest() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("32 John St");
        person.setCity("New York");
        person.setZip("21654");
        person.setPhone("4105551212");
        person.setEmail("123@gmail.com");

        // When
        when(this.personService.updatePerson(any(), any(), any())).thenReturn(person);

        // Then
        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/person")
                .param("firstName", " ")
                .param("lastName", "Doe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updatePersonLastNameBlankTest() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("32 John St");
        person.setCity("New York");
        person.setZip("21654");
        person.setPhone("4105551212");
        person.setEmail("123@gmail.com");

        // When
        when(this.personService.updatePerson(any(), any(), any())).thenReturn(person);

        // Then
        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/person")
                .param("firstName", "John")
                .param("lastName", " ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updatePersonFirstNameEmptyTest() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("32 John St");
        person.setCity("New York");
        person.setZip("21654");
        person.setPhone("4105551212");
        person.setEmail("123@gmail.com");

        // When
        when(this.personService.updatePerson(any(), any(), any())).thenReturn(person);

        // Then
        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/person")
                .param("firstName", "")
                .param("lastName", "Doe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updatePersonLastNameEmptyTest() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("32 John St");
        person.setCity("New York");
        person.setZip("21654");
        person.setPhone("4105551212");
        person.setEmail("123@gmail.com");

        // When
        when(this.personService.updatePerson(any(), any(), any())).thenReturn(person);

        // Then
        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/person")
                .param("firstName", "John")
                .param("lastName", "")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deletePersonTest() throws Exception {
        doNothing().when(personService).deletePerson(any(String.class), any(String.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person")
                .param("firstName", "John")
                .param("lastName", "Doe");
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void deletePersonFirstNameBlankTest() throws Exception {
        doNothing().when(personService).deletePerson(any(String.class), any(String.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person")
                .param("firstName", " ")
                .param("lastName", "Doe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }

    @Test
    void deletePersonLastNameBlankTest() throws Exception {
        doNothing().when(personService).deletePerson(any(String.class), any(String.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person")
                .param("firstName", "John")
                .param("lastName", " ");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }

    @Test
    void deletePersonFirstNameEmptyTest() throws Exception {
        doNothing().when(personService).deletePerson(any(String.class), any(String.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person")
                .param("firstName", "")
                .param("lastName", "Doe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }

    @Test
    void deletePersonLastNameEmptyTest() throws Exception {
        doNothing().when(personService).deletePerson(any(String.class), any(String.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person")
                .param("firstName", "John")
                .param("lastName", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(status().isNotFound());
    }
}

