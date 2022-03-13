package com.example.safetynetalerts.service;

import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.utils.MapperUtils;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ViewResolver;

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

    @Autowired
    private MapperUtils mapperUtils;

    public List<PersonInfoDTO>  toPersonInfo(String firstName, String lastName) {
        List<Person> persons = personService.searchPerson(firstName, lastName);
        List<MedicalRecord> medicalRecordList = medicalRecordService.getMedicalRecords(firstName, lastName);

        return persons.stream()
                .map(p -> {
                    List<MedicalRecord> medicalRecord = medicalRecordService.getMedicalRecords(persons);
                    return mapperUtils.toPersonDTO(persons, medicalRecord)
                })
                .collect(Collectors.toList());

    }
}
