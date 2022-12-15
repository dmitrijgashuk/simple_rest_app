package com.dhashuk.example.service;

import com.dhashuk.example.controller.exeption.PersonNotFoundException;
import com.dhashuk.example.db.Person;
import com.dhashuk.example.dto.PersonBirthdayDTO;
import com.dhashuk.example.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonBirthdayDTO getPersonAgeDataById(String id){
        LocalDate currentDate = LocalDate.now();

        Person person = getPersonById(id);
        LocalDate birthday = person.getBirthday();

        int personAge = Period.between(birthday,currentDate).getYears();

        return PersonBirthdayDTO.builder()
                .firstName(person.getFirstName())
                .secondName(person.getSecondName())
                .age(personAge)
                .build();
    }

    private Person getPersonById(String id){
        Long longID = Long.parseLong(id);
        return personRepository.findById(longID)
                .orElseThrow(PersonNotFoundException::new);
    }

}
