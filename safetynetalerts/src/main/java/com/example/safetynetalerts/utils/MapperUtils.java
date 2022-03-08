package com.example.safetynetalerts.utils;

import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

    public PersonInfoDTO toPersonDTO(Person person, MedicalRecord medicalRecord) {

        PersonInfoDTO personDTO = new PersonInfoDTO();
        personDTO.setLastName(person.getLastName());
        personDTO.setAddress(person.getAddress());
        personDTO.setEmail(person.getEmail());
        personDTO.setMedications(medicalRecord.getMedications());
        personDTO.setAllergies(medicalRecord.getAllergies());

        return personDTO;
    }
}
