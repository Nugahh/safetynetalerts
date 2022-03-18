package com.example.safetynetalerts.service;

import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private MapperUtils mapperUtils;

    public List<PersonInfoDTO>  toPersonInfo(String firstName, String lastName) {
        Person persons = personService.searchPerson(firstName, lastName);
        MedicalRecord medicalRecordList = medicalRecordService.getMedicalRecords(firstName, lastName);

        return personService.searchPersonByLastName(lastName).stream()
                .map(p -> {
                    MedicalRecord mr = medicalRecordService.getMedicalRecords(p.getFirstName(), p.getLastName());
                    return mapperUtils.toPersonDTO(p, mr);
                }).collect(Collectors.toList()) ;
    }
}
