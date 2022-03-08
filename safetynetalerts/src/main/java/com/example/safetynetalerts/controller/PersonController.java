package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PersonController {

    private final static Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    PersonService personService;

    @GetMapping(value ="/person")
    public List<Person> getPersons() throws IOException {
        return personService.getPersons();
    }

   /* @GetMapping(value ="/person/{name}")
    public ResponseEntity<Person> getPersons(String name) throws IOException {
        Person person = personService.findPerson(name);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }*/
   /* @PostMapping(value ="/person/{name}")
    @PutMapping(value ="/person")
    @DeleteMapping*/

}
