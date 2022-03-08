package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FireStationController {

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/firestation")
    public List<FireStation> getFireStation() {
        return fireStationService.getFireStation();
    }
}
