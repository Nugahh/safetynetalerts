package com.example.safetynetalerts.service;

import com.example.safetynetalerts.DTO.*;
import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.utils.CalculateAge;
import com.example.safetynetalerts.utils.MapperUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private MapperUtils mapperUtils;

    private static final Logger LOGGER = LogManager.getLogger(AlertService.class);

    public List<PersonInfoDTO> toPersonInfo(String firstName, String lastName) {

        return personService.searchPersonByLastName(lastName).stream()
                .map(p -> {
                    MedicalRecord mr = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                    PersonInfoDTO personInfoDTOList = mapperUtils.toPersonDTO(p, mr);
                    if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                        LOGGER.info("List of Person generated");
                        personInfoDTOList.setFirstName(firstName);
                    }
                    return personInfoDTOList;
                }).collect(Collectors.toList());

    }

    public List<CommunityEmailDTO> toCommunityEmail(String city) {

        return personService.searchEmailByCity(city).stream()
                .map(p -> mapperUtils.toCommunityEmailDTO(p)).collect(Collectors.toList());
    }

    public List<FloodDTO> toFlood(List<Integer> stations) {

            return fireStationService.getFireStationByStations(stations).stream()
                    .map(s -> {
                        FloodDTO flood = new FloodDTO();
                        List<FloodPersonDTO> floodPersonList = personService.searchByAddress(s.getAddress()).stream()
                                .map(p -> {
                                    MedicalRecord mr = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                                    FloodPersonDTO floodPerson = new FloodPersonDTO();
                                    floodPerson.setLastName(p.getLastName());
                                    floodPerson.setBirthdate(new CalculateAge().calculateAge(mr.getBirthdate()));
                                    floodPerson.setPhone(p.getPhone());
                                    floodPerson.setMedications(mr.getMedications());
                                    floodPerson.setAllergies(mr.getAllergies());
                                    return floodPerson;
                                }).collect(Collectors.toList());
                        flood.setAddress(s.getAddress());
                        flood.setPersons(floodPersonList);
                        return flood;
                    }).collect(Collectors.toList());

    }

    public List<PersonInfoByAddressDTO> toPersonInfoByAddress(String address) {

        return personService.searchByAddress(address).stream()
                .map(p -> {
                    FireStation fs = fireStationService.getFireStationByAddressList(p.getAddress());
                    MedicalRecord mr = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                    return mapperUtils.toPersonInfoByAddressDTO(p, mr, fs);
                }).collect(Collectors.toList());
    }

    public List<String> toPhoneAlert(Integer station) {

        return fireStationService.getFireStationByStation(station).stream()
                .map(FireStation::getAddress)
                .flatMap(address -> personService.searchByAddress(address).stream()
                        .map(Person::getPhone))
                .filter(phone -> (phone != null && !phone.isBlank()))
                .collect(Collectors.toList());
    }

    public List<ChildAlertDTO> toChildAlertByAddress(String address) {

        List<Person> personList = personService.searchByAddress(address);
        List<ChildAlertDTO> children = new ArrayList<>();
        List<Person> family = new ArrayList<>();
        personList.forEach(p -> {
            CalculateAge calc = new CalculateAge();
            MedicalRecord mr = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
            if (calc.calculateAge(mr.getBirthdate()) <= 18) {
                children.add(mapperUtils.toChildAlertDTO(p, mr));
            } else {
                family.add(p);
            }
        });
        children.forEach(p -> {
            p.setFamily(family);
        });
        return children;
    }

    public List<PersonInfoByStationDTO> toPersonInfoByStation(Integer station) {

        return fireStationService.getFireStationByStation(station).stream()
                .map(FireStation::getAddress)
                .flatMap(address -> personService.searchByAddress(address).stream()
                        .map(p -> {
                            {
                                 CountDTO count = new CountDTO();
                                 int child = 0;
                                 int adult = 0;
                                 CalculateAge calc = new CalculateAge();
                                 MedicalRecord mr = medicalRecordService.getMedicalRecordsByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                                    if (calc.calculateAge(mr.getBirthdate()) <= 18){
                                        child++;
                                        count.setChild(count.getChild(child));

                                    } else {
                                        adult++;
                                        count.setAdult(count.getAdult(adult));
                                    }
                                return mapperUtils.toPersonInfoByStationDTO(p);
                            }
                        }))
                .collect(Collectors.toList());
    }
}