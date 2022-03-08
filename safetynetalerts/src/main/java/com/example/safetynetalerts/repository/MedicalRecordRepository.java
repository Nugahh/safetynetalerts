package com.example.safetynetalerts.repository;

import com.example.safetynetalerts.model.MedicalRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MedicalRecordRepository implements CrudRepository<MedicalRecord, String> {

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    @Override
    public <S extends MedicalRecord> S save(S entity) {
        List<MedicalRecord> addMedicalRecordList = this.findAll();
        addMedicalRecordList.add(entity);
        return entity;
    }

    @Override
    public <S extends MedicalRecord> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MedicalRecord> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<MedicalRecord> findAll() {
        return this.medicalRecordList;
    }

    @Override
    public List<MedicalRecord> findAllById(Iterable<String> strings) {
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
    public void delete(MedicalRecord entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends MedicalRecord> entities) {

    }

    @Override
    public void deleteAll() {

    }

   /* public  ArrayList<MedicalRecord> getMedicalRecordList() {
        return this.medicalRecordList;
    }

    public ArrayList<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) {
        ArrayList<MedicalRecord> addMedicalRecordList = getMedicalRecordList();
        addMedicalRecordList.add(medicalRecord);
        return addMedicalRecordList;
    }*/
}
