package com.example.safetynetalerts.service;

import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.PersonRepository;
import com.example.safetynetalerts.utils.MapperUtils;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MapperUtils mapperUtils;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public List<PersonInfoDTO> getPersonsInfo(){

    }
}
