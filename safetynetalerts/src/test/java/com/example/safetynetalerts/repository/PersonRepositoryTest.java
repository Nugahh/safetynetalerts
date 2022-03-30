package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.service.JSONReaderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonRepositoryTest {

    private PersonRepository personRepository;
    private ArrayList<Person> personList;
    private JSONReaderService jsonReaderService;

 /*   @BeforeEach
    void setUp() {
        personRepository = new PersonRepository();
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        personList = new ArrayList<>();
        personList.add(person);
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

    }*/
}
