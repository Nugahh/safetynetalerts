package com.example.safetynetalerts.DTO;

import java.util.List;

public class FloodDTO {

    private String address;
    private List<FloodPersonDTO> persons;


    public List<FloodPersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<FloodPersonDTO> persons) {
        this.persons = persons;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
