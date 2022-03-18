package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.PersonRepository;
import com.example.safetynetalerts.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(String firstName, String lastName) {
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    public Person addPerson(Person person){
        return personRepository.addPerson(person);
    }

    public Person searchPerson(String firstName, String lastName) {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            return personRepository.findById(lastName + firstName);
        }
        return null;
    }
    public List<Person> searchPersonByLastName(String lastName){
        return personRepository.findByLastName(lastName);
    }
}
