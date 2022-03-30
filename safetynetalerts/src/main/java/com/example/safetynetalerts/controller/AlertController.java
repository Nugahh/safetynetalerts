package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.DTO.*;
import com.example.safetynetalerts.service.AlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlertController {

    @Autowired
    private AlertService alertService;

    private static final Logger LOGGER = LogManager.getLogger(AlertController.class);

    @GetMapping(value ="/personInfo" )
    public List<PersonInfoDTO> getPersons(@RequestParam String firstName, @RequestParam String lastName) {
        return alertService.toPersonInfo(firstName, lastName);
    }

    @GetMapping(value ="/communityEmail")
    public List<CommunityEmailDTO> getEmail(@RequestParam String city){
        return alertService.toCommunityEmail(city);
    }

    @GetMapping(value ="/flood/stations")
    public List<FloodDTO> getPersonByStation(@RequestParam List<Integer> stations){
        return alertService.toFlood(stations);
    }

    @GetMapping(value ="/fire")
    public List<PersonInfoByAddressDTO> getPersonByAddress(@RequestParam String address){
        System.out.println(address);
        return alertService.toPersonInfoByAddress(address);
    }

    @GetMapping(value ="/phoneAlert")
    public List<String> getPhoneByStation(@RequestParam Integer firestation){
        return alertService.toPhoneAlert(firestation);
    }

    @GetMapping(value ="/childAlert")
    public List<ChildAlertDTO> getChildByAddress(@RequestParam String address){
        System.out.println(address);
        return alertService.toChildAlertByAddress(address);
    }

    @GetMapping(value ="/firestations")
    public List<PersonInfoByStationDTO> getPersonByStation(@RequestParam Integer stationNumber){
        return alertService.toPersonInfoByStation(stationNumber);
    }
}
