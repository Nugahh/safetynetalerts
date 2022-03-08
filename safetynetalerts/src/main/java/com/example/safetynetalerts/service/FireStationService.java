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
}
