package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }/*

    public Person findPerson(String name) throws IOException {
        return personRepository.findPerson(name);
    }*/
}
