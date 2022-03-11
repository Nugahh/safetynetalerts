package com.example.safetynetalerts.controller;

import com.example.safetynetalerts.DTO.PersonInfoDTO;
import com.example.safetynetalerts.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping(value ="/personInfo" )
    public List<PersonInfoDTO> getPersons(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
        return alertService.toPersonInfo(Optional.of(firstName), Optional.of(lastName));
    }
}
