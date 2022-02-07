package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class JSONReaderService {

    public JSONReaderRepository() throws IOException {}

    //private final static ObjectMapper mapper = new ObjectMapper();
    //JsonNode node = mapper.readTree(Paths.get("src/main/resources/Data.json").toFile());

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IFirestationReposioty firestationRepository;

    @Autowired
    private IMedicalRecordRepository medicalRepository;

    @Value("${dataSourceJson}")
    private String filePath;

    @PostConstruct
    public void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(new FileInputStream(filePath));
            loadPersons(node);
            loadFirestation(node);
            loadMedical(node)
        }

    }

    public void loadPersons(Json node) {
        JsonNode persons = node.path("persons");

        for (JsonNode nodePerson : persons) {

            Person person = new Person();
            person.setFirstName(nodePerson.path("firstName").asText());
            //tous les autres
            personRepository.addPerson(person);
        }
    }

    public ArrayList<Person> readPersonList() throws IOException {

//        ArrayNode persons = (ArrayNode) node.get("persons");

        ArrayList<Person> personList = mapper.readValue(
                new File("src/main/resources/Data.json"),
                new TypeReference<>(){});

//        ArrayList<Person> personList = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++) {
            JSONObject person = (JSONObject) personList.get(i);
            personList.add(new Person(
                    personList.get("firstName").toString(),
                    person.path("lastName").asText(),
                    node.path("address").asText(),
                    node.path("city").asText(),
                    node.path("zip").asText(),
                    node.path("phone").asText(),
                    node.path("email").asText()));
        }
        return personList;
    }

    public HashMap<Integer, FireStation> readFireStationList() throws JsonProcessingException {

        FireStation json = mapper.readValue("src/main/resources/Data.json", FireStation.class);

//        ArrayNode fireStations = (ArrayNode) node.get("fireStations");

        HashMap<Integer, FireStation> fireStationList = new HashMap<>();
        /*for (JsonNode fireStation : json) {
            fireStationList.get(new FireStation(
                    fireStation.path("address").asText(),
                    fireStation.path("station").asInt()));
        }*/
        return fireStationList;
    }

    public ArrayList<MedicalRecord> readMedicalRecordsList() {

        ArrayNode medicalRecords = (ArrayNode) node.get("medicalRecords");

        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        for (JsonNode medicalRecord : medicalRecords) {
            medicalRecordList.add(new MedicalRecord(
                    medicalRecord.path("firstName").asText(),
                    medicalRecord.path("lastName").asText(),
                    medicalRecord.path("birthdate").asText(),
                    medicalRecord.path("medications").asText(),
                    medicalRecord.path("allergies").asText()));
        }
        return medicalRecordList;
    }
}


