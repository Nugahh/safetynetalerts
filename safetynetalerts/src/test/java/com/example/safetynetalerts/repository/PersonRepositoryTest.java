package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.DAO.DataBaseDAO;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/*@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonRepositoryTest {

    private PersonRepository personRepository;
    private ArrayList<Person> personList;
    private JSONReaderRepository jsonReaderRepository;

    @BeforeEach
    void setUp() throws IOException {
        personRepository = new PersonRepository(jsonReaderRepository);
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        personList = new ArrayList<>();
        personList.add(person);
        when(mockDataBaseDAO.getPersonList()).thenReturn(personList);
    }

    @AfterEach
    void cleanUp() {
        personList.clear();
    }

    @Test
    void getPersonsShouldCallGetPersonList() {
        personRepository.getAllPersons();
        verify(mockDataBaseDAO).getPersonList();
    }

    @Test
    void savePersonShouldReturnTrue() {
        Person person = new Person();
        assertTrue(personRepository.savePerson(person));
    }
    @Test
    void getPersonByIdShouldReturnAnOptionalNotNull() {
        assertNotNull(personRepository.getPerson(personList.get(0).getIdPerson()));

    }

    @Test
    void getPersonByFirstNameAndLastNameShouldReturnOptionalNotNull() {
        assertNotNull(personRepository.getPerson("Lo", "Frazier"));
    }

    @Test
    void deletePersonShouldReturnTrueIfPersonDeleted() {
        assertTrue(personRepository.deletePerson(personList.get(0)));
    }

    @Test
    void getPersonsTest(){

    }
}*/
