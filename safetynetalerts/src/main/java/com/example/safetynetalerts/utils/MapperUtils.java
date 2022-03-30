package com.example.safetynetalerts.utils;

import com.example.safetynetalerts.DTO.*;
import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {

    CalculateAge calc = new CalculateAge();

    public PersonInfoDTO toPersonDTO(Person person, MedicalRecord medicalRecord) {

        PersonInfoDTO personInfoDTO = new PersonInfoDTO();

        personInfoDTO.setLastName(person.getLastName());
        personInfoDTO.setAddress(person.getAddress());
        personInfoDTO.setBirthdate(calc.calculateAge(medicalRecord.getBirthdate()));
        personInfoDTO.setEmail(person.getEmail());
        personInfoDTO.setMedications(medicalRecord.getMedications());
        personInfoDTO.setAllergies(medicalRecord.getAllergies());

        return personInfoDTO;
    }

    public CommunityEmailDTO toCommunityEmailDTO(Person person){

        CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO();

        communityEmailDTO.setEmail(person.getEmail());

        return communityEmailDTO;
    }

    public PersonInfoByAddressDTO toPersonInfoByAddressDTO(Person person, MedicalRecord medicalRecord, FireStation station){

        PersonInfoByAddressDTO personInfoByAddressDTO = new PersonInfoByAddressDTO();

        personInfoByAddressDTO.setLastName(person.getLastName());
        personInfoByAddressDTO.setPhone(person.getPhone());
        personInfoByAddressDTO.setBirthdate(calc.calculateAge(medicalRecord.getBirthdate()));
        personInfoByAddressDTO.setMedications(medicalRecord.getMedications());
        personInfoByAddressDTO.setAllergies(medicalRecord.getAllergies());
        personInfoByAddressDTO.setStation(station.getStation());

        return personInfoByAddressDTO;
    }

    public PhoneAlertDTO toPhoneAlertDTO(Person person){

        PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();

        phoneAlertDTO.setPhone(person.getPhone());

        return phoneAlertDTO;
    }

    public ChildAlertDTO toChildAlertDTO(Person person, MedicalRecord medicalRecord){

        ChildAlertDTO childAlertDTO = new ChildAlertDTO();

        childAlertDTO.setFirstName(person.getFirstName());
        childAlertDTO.setLastName(person.getLastName());
        childAlertDTO.setBirthdate(calc.calculateAge(medicalRecord.getBirthdate()));

        return childAlertDTO;
    }

    public PersonInfoByStationDTO toPersonInfoByStationDTO(Person person){

        PersonInfoByStationDTO personInfoByStationDTO = new PersonInfoByStationDTO();

        personInfoByStationDTO.setFirstName(person.getFirstName());
        personInfoByStationDTO.setLastName(person.getLastName());
        personInfoByStationDTO.setAddress(person.getAddress());
        personInfoByStationDTO.setPhone(person.getPhone());

        return personInfoByStationDTO;
    }
}
