package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.FireStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FireStationRepository implements CrudRepository<FireStation, String> {

    private List<FireStation> firestationList = new ArrayList<>();

    @Override
    public <S extends FireStation> S save(S entity) {
        List<FireStation> addFirestationList = this.findAll();
        addFirestationList.add(entity);
        return null;
    }

    @Override
    public <S extends FireStation> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<FireStation> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<FireStation> findAll() {
        return this.firestationList;
    }

    @Override
    public Iterable<FireStation> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(FireStation entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends FireStation> entities) {

    }

    @Override
    public void deleteAll() {

    }



    /*private ArrayList<FireStation> fireStationList = new ArrayList<>();

    public FireStationRepository() {
    }

    public ArrayList<FireStation> getFireStationList(){
        return this.fireStationList;
    }

    public ArrayList<FireStation> addFireStation(FireStation fireStation) {
        ArrayList<FireStation> addFireStationList = getFireStationList();
        addFireStationList.add(fireStation);
        return addFireStationList;
    }*/
}