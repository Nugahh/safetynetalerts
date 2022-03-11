package com.example.safetynetalerts.service;

import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.utils.MapperUtils;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    public List<PersonInfoDTO>  toPersonInfo(Optional<String> firstName, Optional<String> lastName) {
        List<Person> persons = personService.searchPerson(firstName, lastName);
        List<MedicalRecord> medicalRecordList = medicalRecordService.getMedicalRecords(firstName, lastName);

        return persons.stream().map(p -> {
            PersonInfoDTO personInfoDTO = new PersonInfoDTO();
            Optional<MedicalRecord> medicalRecordByPerson = medicalRecordList.stream()
                    .filter(record -> record.getFirstName().equals(p.getFirstName())
                            && record.getLastName().equals(p.getLastName())).findFirst();
            if (medicalRecordByPerson.isPresent()) {
                personInfoDTO.setMedications(medicalRecordByPerson.get().getMedications());
                personInfoDTO.setAllergies(medicalRecordByPerson.get().getAllergies());
            }
            personInfoDTO.setLastName(p.getLastName());
            personInfoDTO.setAddress(p.getAddress());
            personInfoDTO.setEmail(p.getEmail());
            System.out.println(personInfoDTO);
            System.out.println(medicalRecordByPerson);
            System.out.println(persons);
            return personInfoDTO;
        }).collect(Collectors.toList());
    }
}
