package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.FireStation;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FireStationRepository{

    private List<FireStation> fireStationList = new ArrayList<>();

    public List<FireStation> findAll() {
        return this.fireStationList;
    }

    public FireStation addFireStation(FireStation firestation) {
        this.fireStationList.add(firestation);
        return firestation;
    }

    public FireStation updateFireStation(FireStation fireStationOld, String address) {

        FireStation fireStationNew = findStationByAddress(address);
        fireStationNew.setStation(fireStationOld.getStation());

        return fireStationList.set(fireStationList.indexOf(findStationByAddress(address)), fireStationNew);
    }

    public void deleteByAddress(String address) {
        this.fireStationList.removeIf(s -> s.getAddress().equals(address));
    }

    public void deleteByStation(Integer station) {
        this.fireStationList.removeIf(s -> s.getStation().equals(station));
    }

    public List<FireStation> findByStations(List<Integer> stations) {
        return this.fireStationList.stream()
                .filter(s -> stations.contains(s.getStation()))
                .collect(Collectors.toList());
    }

    public List<FireStation> findByStation(Integer station) {
        return this.fireStationList.stream()
                .filter(s -> station.equals(s.getStation()))
                .collect(Collectors.toList());
    }

    public FireStation findStationByAddress(String address) {
        return this.fireStationList.stream()
                .filter(s -> (s.getAddress().equals(address))).findAny().orElseThrow();
    }
}
