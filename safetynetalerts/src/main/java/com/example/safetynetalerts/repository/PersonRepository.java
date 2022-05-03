package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository{

    private final List<Person> personList = new ArrayList<>();

    public List<Person> findAll() {
        return this.personList;
    }

    public Person addPerson(Person person) {
        personList.add(person);
        return person;
    }

    public Person updatePerson(Person personOld, String firstName, String lastName) throws IndexOutOfBoundsException {

            Person personNew = findByFirstNameAndLastName(firstName, lastName);
            personNew.setAddress(personOld.getAddress());
            personNew.setCity(personOld.getCity());
            personNew.setZip(personOld.getZip());
            personNew.setPhone(personOld.getPhone());
            personNew.setEmail(personOld.getEmail());

           return personList.set(personList.indexOf(findByFirstNameAndLastName(firstName, lastName)), personNew);
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.personList.removeIf(person ->
                person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
    }

    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        return this.personList.stream()
                .filter(person -> (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))).findAny().orElseThrow();
    }

    public List<Person> findByLastName(String lastName){
        return this.personList.stream()
                .filter((person -> person.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    public List<Person> findByCity(String city){
        return this.personList.stream()
                .filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public List<Person> findByAddress(String address){
        return this.personList.stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }
}
