package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping(value ="/personInfo" )
    public List<PersonInfoDTO> getPersons(@RequestParam String firstName, @RequestParam String lastName) {
        return alertService.toPersonInfo(firstName, lastName);
    }
}
