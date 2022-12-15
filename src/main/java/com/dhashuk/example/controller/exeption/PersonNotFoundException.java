package com.dhashuk.example.controller.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException{

}
