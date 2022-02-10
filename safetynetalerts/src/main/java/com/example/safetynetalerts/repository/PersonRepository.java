package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;

@Repository
public class PersonRepository extends JSONReaderService {

    private ArrayList<Person> personList = new ArrayList<>();

    public PersonRepository() throws IOException {
    }

    public ArrayList<Person> getPersonList () throws IOException {
        return this.readPersonList();
    }

    public ArrayList<Person> addPerson(Person person) throws IOException {
        ArrayList<Person> addPersonList = getPersonList();
        addPersonList.add(person);
        return addPersonList;
    }

    public ArrayList<Person> deletePerson(Person person) throws IOException {
        ArrayList<Person> deletePersonOfList = getPersonList();
        deletePersonOfList.remove(person);
        return deletePersonOfList;
    }

    public Person updatePerson(Person person,String name) throws IOException {
        ArrayList<Person> updateAPerson = getPersonList();
        for (int i = 0; i < updateAPerson.size(); i++) {
            if (updateAPerson.get(i).getFirstName().contains(name)) {
                updateAPerson.set(i, person);
            }
            return updateAPerson.get(i);
        }
        return updatePerson(person,name);
    }

    public Person findPerson(String name) throws IOException {
        ArrayList<Person> personListByName = getPersonList();
        for (Person personFind : personListByName) {
            if (personFind.getFirstName().contains(name)) {
                return personFind;
            }
        }
        return findPerson(name);
    }
}
