package org.example.service;

import org.example.model.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> findAll();
}
