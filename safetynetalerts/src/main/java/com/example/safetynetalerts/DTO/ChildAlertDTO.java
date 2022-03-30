package com.example.safetynetalerts.DTO;

import com.example.safetynetalerts.model.Person;

import java.util.List;


public class ChildAlertDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private List<Person> family;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBirthdate() {
        return age;
    }

    public void setBirthdate(Integer age) {
        this.age = age;
    }

    public List<Person> getFamily() {
        return family;
    }

    public void setFamily(List<Person> family) {
        this.family = family;
    }
}
