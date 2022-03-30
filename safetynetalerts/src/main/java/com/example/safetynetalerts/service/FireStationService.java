package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.model.MedicalRecord;
import com.example.safetynetalerts.model.Person;
import com.example.safetynetalerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService {


    @Autowired
    FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public void deleteFireStation(FireStation station) {
        fireStationRepository.deleteByStationOrAddress(station);
    }

    public FireStation addFireStation(FireStation station){
        return fireStationRepository.addFireStation(station);
    }

    public List<FireStation> getFireStation() {
        return fireStationRepository.findAll();
    }

    public List<FireStation> getFireStationByStations(List<Integer> stations){
        return fireStationRepository.findByStations(stations);
    }
    public List<FireStation> getFireStationByStation(Integer station){
        return fireStationRepository.findByStation(station);
    }

    public FireStation getFireStationByAddressList(String address){
        if (!address.isEmpty()){
            return fireStationRepository.findStationByAddress(address);
        }
        return null;
    }
}

