package com.example.safetynetalerts.service;

import com.example.safetynetalerts.model.FireStation;
import com.example.safetynetalerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationService {


    @Autowired
    FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public List<FireStation> getFireStation() {
        return fireStationRepository.findAll();
    }

    public void deleteFireStationByAddress(String address) {
        fireStationRepository.deleteByAddress(address);
    }

    public void deleteFireStationByStation(Integer station) {
        fireStationRepository.deleteByStation(station);
    }

    public FireStation addFireStation(FireStation station){
        return fireStationRepository.addFireStation(station);
    }

    public FireStation updateFireStation(FireStation fireStationOld, String address){
        return fireStationRepository.updateFireStation(fireStationOld, address);
    }

    public List<FireStation> findFireStationByStations(List<Integer> stations){
        return fireStationRepository.findByStations(stations);
    }

    public List<FireStation> findFireStationByStation(Integer station){
        return fireStationRepository.findByStation(station);
    }

    public FireStation findFireStationByAddressList(String address){
            return fireStationRepository.findStationByAddress(address);
    }
}

