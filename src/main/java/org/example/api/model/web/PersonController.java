package org.example.api.model.web;

import org.example.model.PersonDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface PersonController extends WebController<PersonDto, Integer> {

    @GetMapping
    List<PersonDto> index();
}
