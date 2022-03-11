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

    public List<Person> searchPerson(Optional<String> firstName, Optional<String> lastName){
        if (firstName.isPresent() && lastName.isPresent()){
            return personRepository.findById(lastName.get() + firstName.get());
        }
        if (firstName.isPresent()){
            return personRepository.findByFirstName(firstName.get());
        }
        if (lastName.isPresent()){
            return personRepository.findByLastName(lastName.get());
        }
        return personRepository.findAll();
    }
}
