package com.example.safetynetalerts.utils;

import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

    public PersonInfoDTO toPersonDTO(Person person, MedicalRecord medicalRecord) {

        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setLastName(person.getLastName());
        personInfoDTO.setAddress(person.getAddress());
        personInfoDTO.setEmail(person.getEmail());
        personInfoDTO.setMedications(medicalRecord.getMedications());
        personInfoDTO.setAllergies(medicalRecord.getAllergies());

        return personInfoDTO;
    }
}
