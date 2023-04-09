package org.example.api.model.web.impl;

import org.example.api.model.web.PersonController;
import org.example.model.PersonDto;
import org.example.service.impl.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController implements PersonController {

    private final PeopleService personService;

    @Autowired
    public PeopleController(PeopleService personService) {
        this.personService = personService;
    }

    public List<PersonDto> index() {
        return personService.findAll();
    }

    public PersonDto show(final Integer id) {
        return personService.findById(id);
    }

    public Integer create(final PersonDto person) {
        return personService.save(person);
    }

    public PersonDto edit(final PersonDto person, final Integer id) {
        return personService.update(id, person);
    }

    public void delete(final Integer id) {
        personService.delete(id);
    }
}
