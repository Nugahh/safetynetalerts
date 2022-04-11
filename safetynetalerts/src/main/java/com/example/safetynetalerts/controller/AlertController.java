package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.DTO.*;
import com.example.safetynetalerts.service.AlertService;
import com.example.safetynetalerts.service.FireStationService;
import com.example.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private PersonService personService;

    @Autowired
    private FireStationService fireStationService;

    private static final Logger logger = LogManager.getLogger(AlertController.class);

    @GetMapping(value = "/personInfo")
    public ResponseEntity<PersonInfoDTO> getPersons(@RequestParam String firstName, @RequestParam String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            logger.error("Firstname or Lastname blank");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("List of Person generated");
            return new ResponseEntity(alertService.toPersonInfo(firstName, lastName), HttpStatus.OK);
        }
}

    @GetMapping(value ="/communityEmail")
    public ResponseEntity<CommunityEmailDTO> getEmail(@RequestParam String city){
        if (city.equals(""))
        {
            logger.error("City is blank");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (personService.findEmailByCity(city).isEmpty())
        {
            logger.error("City doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else{
            logger.info("All email from city: " + city);
            return new ResponseEntity(alertService.toCommunityEmail(city), HttpStatus.OK);
        }
    }

    @GetMapping(value ="/flood/stations")
    public ResponseEntity<FloodDTO> getPersonsByStation(@RequestParam List<Integer> stations){
        if (fireStationService.findFireStationByStations(stations).isEmpty())
        {
            logger.error("Station doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Household by stations");
            return new ResponseEntity(alertService.toFlood(stations), HttpStatus.OK);
        }
    }

    @GetMapping(value ="/fire")
    public ResponseEntity<PersonInfoByAddressDTO> getPersonByAddress(@RequestParam String address){
        if (personService.findByAddress(address).isEmpty())
        {
            logger.error("Address doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (address.isBlank())
        {
            logger.error("Address is blank");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("List of person by address");
            return new ResponseEntity(alertService.toPersonInfoByAddress(address), HttpStatus.OK);
        }
    }

    @GetMapping(value ="/phoneAlert")
    public ResponseEntity<String> getPhoneByStation(@RequestParam Integer firestation){
        if (fireStationService.findFireStationByStation(firestation).isEmpty())
        {
            logger.error("Station doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else{
            logger.info("List of phones by station");
            return new ResponseEntity(alertService.toPhoneAlert(firestation), HttpStatus.OK);
        }
    }

    @GetMapping(value ="/childAlert")
    public ResponseEntity<ChildAlertDTO> getChildByAddress(@RequestParam String address){
        if (personService.findByAddress(address).isEmpty())
        {
            logger.error("Address doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (address.isBlank())
        {
            logger.error("Address is blank");
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        } else {
            logger.info("List of child and members of family");
            return new ResponseEntity(alertService.toChildAlertByAddress(address), HttpStatus.OK);
        }
    }

    @GetMapping(value ="/firestations")
    public ResponseEntity<PersonInfoByStationAndCountDTO> getPersonByStation(@RequestParam Integer stationNumber){
        if (fireStationService.findFireStationByStation(stationNumber).isEmpty())
        {
            logger.error("Station doesn't exist");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            logger.info("List of person by station");
            return  new ResponseEntity(alertService.toPersonInfoByStation(stationNumber), HttpStatus.OK);
        }
    }
}
