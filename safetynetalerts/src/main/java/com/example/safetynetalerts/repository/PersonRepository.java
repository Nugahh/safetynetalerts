package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonRepository{

    private List<Person> personList = new ArrayList<>();

    public Person addPerson(Person person){
        this.personList.add(person);
        return person;
    }
    public List<Person> findAll() {
        return this.personList;
    }

    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        this.personList.forEach((Person person) -> {
            if((person.getLastName().equals(lastName) && person.getFirstName().equals(firstName))){
                this.personList.remove(person);
            }
        });
    }

    public Person findByLastNameAndFirstName(String s) {
        return this.personList.stream()
                .filter((Person p) -> (p.getLastName() + p.getFirstName()).equals(s)).findAny().orElseThrow();
    }

    public List<Person> findByLastName(String lastName){
        return this.personList.stream()
                .filter((person -> person.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    public List<Person> findByCity(String city){
        return this.personList.stream()
                .filter((Person person) -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public List<Person> findByAddress(String address){
        return this.personList.stream()
                .filter((person) -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }
    /*public String findAllFirstName(){
        return this.personList.stream()
                .map(Person::getFirstName)
                .collect(Collectors.joining());
    }*/
}
