package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.Person;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

    private final PersonRepository personRepository = new PersonRepository();

    @Test
    @DisplayName("Given new person, when delete by firstname and lastname, then person deleted")
    void deleteByFirstNameAndLastNameTest() {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("135 John street");
        person.setCity("Paris");
        person.setZip("75000");
        person.setPhone("0611223344");
        person.setEmail("john.doe@gmail.com");

        // When
        personRepository.addPerson(person);
        personRepository.deleteByFirstNameAndLastName("John", "Doe");

        // Then
        assertTrue(personRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Given new person, when delete by lastname only, then person remains")
    void deleteByFirstNameAndLastNameFirstNameBlankTest() {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("135 John street");
        person.setCity("Paris");
        person.setZip("75000");
        person.setPhone("0611223344");
        person.setEmail("john.doe@gmail.com");

        // When
        personRepository.addPerson(person);
        personRepository.deleteByFirstNameAndLastName("", "Doe");

        // Then
        assertFalse(personRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Given new person, when delete by firstname only, then person remains")
    void deleteByFirstNameAndLastNameLastNameBlankTest() {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("135 John street");
        person.setCity("Paris");
        person.setZip("75000");
        person.setPhone("0611223344");
        person.setEmail("john.doe@gmail.com");

        // When
        personRepository.addPerson(person);
        personRepository.deleteByFirstNameAndLastName("John", "");

        // Then
        assertFalse(personRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Given new person, when find by last name only, then person not found")
    void findByFirstNameAndLastNameFirstNameBlankTest() {
        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("135 John street");
        person.setCity("Paris");
        person.setZip("75000");
        person.setPhone("0611223344");
        person.setEmail("john.doe@gmail.com");

        // When
        personRepository.addPerson(person);

        // Then
        assertThrows(NoSuchElementException.class, () -> personRepository.findByFirstNameAndLastName("", "Doe"));

    }

    @Test
    @DisplayName("Given new person, when find by first name only, then person not found")
    void findByFirstNameAndLastNameLastNameBlankTest() {

        // Given
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("135 John street");
        person.setCity("Paris");
        person.setZip("75000");
        person.setPhone("0611223344");
        person.setEmail("john.doe@gmail.com");

        // When
        personRepository.addPerson(person);

        //then
        assertThrows(NoSuchElementException.class, () -> personRepository.findByFirstNameAndLastName("John", ""));

    }


}
