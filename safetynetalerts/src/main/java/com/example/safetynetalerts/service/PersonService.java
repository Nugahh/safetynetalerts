package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ArrayList<Person> getPersons() throws IOException {
        return personRepository.getPersonList();
    }

    public Person findPerson(String name) throws IOException {
        Person personName = personRepository.findPerson(name);
        return personName;
    }
}
