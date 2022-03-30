package com.example.safetynetalerts.controller;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonController {

    private final static Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @GetMapping(value = "/person")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @PostMapping(value = "/person")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping(value = "/person")
    public Person updatePerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @DeleteMapping(value = "/person")
    public ResponseEntity<String> deletePerson(String firstName, String lastName) {
        personService.deletePerson(firstName, lastName);
        if (getPersons().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La personne n'existe pas");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(firstName + " " + lastName + " " + "a été supprimée");
        }


    /*@GetMapping(value ="/person/{name}")
    public ResponseEntity<Person> getPersons(String name) throws IOException {
        Person person = personService.findPerson(name);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }*/
   /* @PostMapping(value ="/person/{name}")
    @PutMapping(value ="/person")

*/
        }
    }

