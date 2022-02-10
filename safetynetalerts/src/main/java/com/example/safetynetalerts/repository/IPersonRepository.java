package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;

public interface IPersonRepository {

        ArrayList<Person> getPersonList ();
        ArrayList<Person> addPerson(Person person);
        ArrayList<Person> deletePerson(Person person);
        Person updatePerson(Person person,String name) throws IOException;
        Person findPerson(String name);
}
