package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository implements CrudRepository<Person, String> {

    private List<Person> personList = new ArrayList<>();

    @Override
    public <S extends Person> S save(S entity) {
        List<Person> addPersonList = this.findAll();
        addPersonList.add(entity);
        return entity;
    }

    @Override
    public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Person> findById(String s) {
        return this.findAll().stream()
                .filter((Person p) -> (p.getLastName() + p.getFirstName()).equals(s))
                .findAny();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Person> findAll() {
        return this.personList;
    }

    @Override
    public Iterable<Person> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {
        this.findAll().forEach((Person person) -> {
            if((person.getLastName() + person.getFirstName()).equals(s)){
                this.findAll().remove(person);
            }
        });
    }

    @Override
    public void delete(Person entity) {
        this.findAll().remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Person> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
