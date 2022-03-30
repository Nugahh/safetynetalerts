package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FireStationRepository{

    private List<FireStation> fireStationList = new ArrayList<>();

    public FireStation addFireStation(FireStation firestation){
        this.fireStationList.add(firestation);
        return firestation;
    }
    public List<FireStation> findAll() {
        return this.fireStationList;
    }

    public void deleteByStationOrAddress(FireStation station) {
        fireStationList.remove(station);
    }

    public List<FireStation> findByStations(List<Integer> stations) {
        return this.fireStationList.stream()
                .filter((FireStation s) -> stations.contains(s.getStation()))
                .collect(Collectors.toList());
    }

    public List<FireStation> findByStation(Integer station) {
        return this.fireStationList.stream()
                .filter((FireStation s) -> station.equals(s.getStation()))
                .collect(Collectors.toList());
    }

    public FireStation findStationByAddress(String address) {
        return this.fireStationList.stream()
                .filter((s) -> (s.getAddress().equals(address))).findAny().orElseThrow();
    }
}
