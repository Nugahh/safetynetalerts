package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.service.FireStationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;

public class FireStationController {

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/firestation")
    public HashMap<Integer, FireStation> getFireStation() throws JsonProcessingException {
        HashMap<Integer, FireStation>fireStationList = fireStationService.getFireStation();
        return fireStationList;
    }

}
