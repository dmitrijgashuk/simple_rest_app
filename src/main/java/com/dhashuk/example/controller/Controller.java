package com.dhashuk.example.controller;

import com.dhashuk.example.dto.PersonBirthdayDTO;
import com.dhashuk.example.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="person", produces = "application/json")
public class Controller {
    private final PersonService personService;

    public Controller(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("{id}")
    public PersonBirthdayDTO getPersonDataById(@PathVariable String id){
        return personService.getPersonAgeDataById(id);
    }
}
