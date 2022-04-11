package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {

    private final static Logger logger = LogManager.getLogger(FireStationController.class);

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/firestation")
    public ResponseEntity<FireStation> getFireStation(){
        logger.info("List of Firestations generated");
        return new ResponseEntity(fireStationService.getFireStation(), HttpStatus.OK);
    }

    @PostMapping(value = "/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation station){
        logger.info("Firestation created");
        return new ResponseEntity(fireStationService.addFireStation(station), HttpStatus.CREATED);
    }

    @PutMapping(value = "/firestation")
    public ResponseEntity<FireStation> updatePerson(@RequestBody FireStation station, @RequestParam String address){
        if (address.isEmpty() || address.isBlank()){
            logger.error("Address not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else{
            logger.info(address + "'s station has been updated");
            return new ResponseEntity(fireStationService.updateFireStation(station, address), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/firestation")
    public ResponseEntity deleteFireStation(@RequestParam String address, Integer station) {
        if ((address.isBlank() || address.isEmpty()) && station == null){
            logger.error("Address or station blank");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (address.isEmpty() || address.isBlank()){
            logger.info("station " + station + " has been deleted");
            fireStationService.deleteFireStationByStation(station);
        } else if (station == null){
            logger.info(address + " station has been deleted");
            fireStationService.deleteFireStationByAddress(address);
        }
        return null;
    }
}
