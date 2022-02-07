package com.example.safetynetalerts.model;

import java.util.ArrayList;
import java.util.List;

public class FireStation {

    private String address;
    private Integer station;


    public FireStation(String address, Integer station) {

        this.address = address;
        this.station = station;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }
}