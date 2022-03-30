package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    }

    public void deletePerson(String firstName, String lastName) {
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

    public Person addPerson(Person person){
        return personRepository.addPerson(person);
    }

    public Person searchPerson(String firstName, String lastName) {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            return personRepository.findByLastNameAndFirstName(lastName + firstName);
        }
        return null;
    }

    public List<Person> searchPersonByLastName(String lastName){
        return personRepository.findByLastName(lastName);
    }

    public List<Person> searchEmailByCity(String city){
       if (!city.isEmpty()) {
           return personRepository.findByCity(city);
       }
        return null;
    }
    public List<Person> searchByAddress(String address){
       if (!address.isEmpty()) {
           return personRepository.findByAddress(address);
       }
        return null;
    }
}