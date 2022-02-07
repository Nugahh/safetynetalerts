package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class PersonController {

    private final static Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    PersonService personService;

    @GetMapping(value ="/person")
    public ArrayList<Person> getPersons() throws IOException {
        return personService.getPersons();
    }

    @GetMapping(value ="/person/{name}")
    public Person getPersonByName(@PathVariable String name) throws IOException {
        Person person = personService.findPerson(name);
        return person;
    }
}
