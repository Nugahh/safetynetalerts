package com.example.safetynetalerts.DAO;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Component
public class DataBaseDAO {

   /* private List<Person> personList = new ArrayList<>();
    private List<FireStation> fireStationList = new ArrayList<>();
    private List<MedicalRecord> medicalRecordList = new ArrayList<>();
    private TreeNode node;

    @PostConstruct
    public void init() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        TypeReference<List<Person>> tRPerson = new TypeReference<>() {
        };
        InputStream iSPerson = TypeReference.class.getResourceAsStream("/persons.json");
        TypeReference<List<FireStation>> tRFireStation = new TypeReference<>() {
        };
        InputStream iSFireStation = TypeReference.class.getResourceAsStream("/fireStations.json");
        TypeReference<List<MedicalRecord>> tRMedicalRecord = new TypeReference<>() {
        };
        InputStream iSMedicalRecord = TypeReference.class.getResourceAsStream("/medicalRecords.json");

        this.personList = mapper.readValue(iSPerson, tRPerson);
        this.fireStationList = mapper.readValue(iSFireStation, tRFireStation);
        this.medicalRecordList = mapper.readValue(iSMedicalRecord, tRMedicalRecord);
    }

    @PostConstruct
    public void createLinks() {
        linkBetweenMedicalRecordAndPerson();
        linkBetweenFireStationAndPerson();
    }

    public void linkBetweenFireStationAndPerson() {
        this.fireStationList
                .forEach(fS -> {
                    List<Person> persons = getIdPersons(fS.getAddress());
                    for (Person person : persons) {
                        person.getIdFireStations().add(fS.getIdFireStation());
                        fS.getIdPersons().add(person.getIdPerson());
                    }
                });
    }

    public void linkBetweenMedicalRecordAndPerson() {
        this.medicalRecordList
                .forEach(mR -> {
                    Optional<Person> person = getPerson(mR.getFirstName(), mR.getLastName());
                    if (person.isPresent()) {
                        mR.setIdPerson(person.get().getIdPerson());
                        person.get().setIdMedicalRecord(mR.getIdMedicalRecord());
                    }
                });
    }

    public Optional<Person> getPerson(String firstName, String lastName) {
        return this.personList.stream()
                .filter(p -> p.getFirstName().equals(firstName)
                        && p.getLastName().equals(lastName))
                .findFirst();
    }

    public List<Person> getIdPersons(String address) {
        return this.personList.stream()
                .filter(p -> p.getAddress().equals(address))
                .collect(Collectors.toList());
    }*/
}
