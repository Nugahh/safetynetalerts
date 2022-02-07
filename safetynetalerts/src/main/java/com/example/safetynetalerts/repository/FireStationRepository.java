package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.DAO.DataBaseDAO;
import com.example.safetynetalerts.model.FireStation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FireStationRepository extends JSONReaderRepository {


    public FireStationRepository() throws IOException {
    }

    public HashMap<Integer, FireStation> getFireStationList() throws JsonProcessingException {
        return this.readFireStationList();
    }

  /*  public ArrayList<FireStation> addFireStation(FireStation fireStation) {
        ArrayList<FireStation> addFireStationList = getFireStationList();
        addFireStationList.add(fireStation);
        return addFireStationList;
    }

    public ArrayList<FireStation> deleteFireStation(FireStation fireStation) {
        ArrayList<FireStation> deleteFireStationList = getFireStationList();
        deleteFireStationList.remove(fireStation);
        return deleteFireStationList;
    }*/

   /* public FireStation findFireStation(String address) {
        ArrayList<FireStation> fireStationList = getFireStationList();
        for (FireStation fireStation : fireStationList) {
            if (fireStationList.get(i).getAddress().toLowerCase().contains(address)) {
                fireStationList.get(i);
            }
            return fireStationName.get(i);
        }
        return findFireStation(address);
    }

    public FireStation updateFireStation(FireStation fireStation, String address) {
        ArrayList<FireStation> updateFireStation = getFireStationList();
        for (int i = 0; i < updateFireStation.size(); i++) {
            if (updateFireStation.get(i).getAddress().contains(address)) {
                updateFireStation.set(i, fireStation);
            }
            return updateFireStation.get(i);
        }
        return updateFireStation(fireStation, address);
    }*/
}