package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationController {

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/firestation")
    public List<FireStation> getFireStation() {
        return fireStationService.getFireStation();
    }

    @PostMapping(value = "/firestation")
    public FireStation addFireStation(@RequestBody FireStation station) {
        return fireStationService.addFireStation(station);
    }

    @PutMapping(value = "/firestation")
    public FireStation updatePerson(@RequestBody FireStation station) {
        return fireStationService.addFireStation(station);
    }
    @DeleteMapping(value = "/firestation")
    public void deleteFireStation(@RequestBody FireStation station) {
        fireStationService.deleteFireStation(station);
    }
}
