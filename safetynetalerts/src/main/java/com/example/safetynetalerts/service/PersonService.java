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

    public List<Person> getPersons() { // get list of Person
        return personRepository.findAll();
    }

    public void deletePerson(String firstName, String lastName) { // delete person by firstName and firstName
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    public Person addPerson(Person person){ // add person to the list
        return personRepository.addPerson(person);
    }

    public Person updatePerson(Person personOld, String firstName, String lastName){ // update person by firstName and lastName
       return personRepository.updatePerson(personOld, firstName, lastName);
    }

    public List<Person> findPersonByLastName(String lastName){ // find person by last name
        return personRepository.findByLastName(lastName);
    }

    public List<Person> findEmailByCity(String city){ // find email by city
           return personRepository.findByCity(city);
    }

    public List<Person> findByAddress(String address){ // find person by address
           return personRepository.findByAddress(address);
    }
}