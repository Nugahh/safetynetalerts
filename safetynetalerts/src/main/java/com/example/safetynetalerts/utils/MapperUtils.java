package com.example.safetynetalerts.utils;

import com.example.safetynetalerts.DTO.PersonDTO;
import com.example.safetynetalerts.model.Person;

public class MapperUtils {

    public PersonDTO toPersonDTO(Person person) {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(person.getFirstName);

        return personDTO;
    }
}
