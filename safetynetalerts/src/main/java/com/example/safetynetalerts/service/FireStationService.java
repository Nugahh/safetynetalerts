package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.repository.FireStationRepository;
import com.example.safetynetalerts.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FireStationService {


    @Autowired
    FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;

    }

    public HashMap<Integer, FireStation> getFireStation() throws JsonProcessingException {
        HashMap<Integer, FireStation> getFireStationList = fireStationRepository.getFireStationList();
        return getFireStationList;
    }
}
