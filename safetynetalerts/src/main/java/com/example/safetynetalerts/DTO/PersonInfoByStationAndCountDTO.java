package com.example.safetynetalerts.DTO;

import java.util.List;

public class PersonInfoByStationAndCountDTO {

    private List<PersonInfoByStationDTO> household;
    private Integer child = 0;
    private Integer adult = 0;

    public Integer getChild() {
        return this.child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public Integer getAdult() {
        return this.adult;
    }

    public void setAdult(Integer adult) {
        this.adult = adult;
    }

    public List<PersonInfoByStationDTO> getHousehold() {
        return household;
    }

    public void setHousehold(List<PersonInfoByStationDTO> household) {
        this.household = household;
    }
}
