package com.example.safetynetalerts.DTO;

import java.util.List;

public class PersonInfoByAddressDTO {

    private Integer station;
    private String lastName;
    private String phone;
    private Integer age;

    private List<String> medications;
    private List<String> allergies;

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public Integer getBirthdate() {
        return age;
    }

    public void setBirthdate(Integer age) {
        this.age = age;
    }
}
