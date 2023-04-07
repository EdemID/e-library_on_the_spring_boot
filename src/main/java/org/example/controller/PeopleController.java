package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.PersonDto;
import org.example.serviece.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService personService;

    @Autowired
    public PeopleController(PeopleService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<PersonDto> index() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDto show(@PathVariable("id") int id) {
        return personService.findByIdWithBooks(id);
    }

    @PostMapping("/new")
    public int create(@RequestBody @Valid PersonDto person) {
        return personService.save(person);
    }

    @PatchMapping("/{id}/edit")
    public PersonDto edit(@RequestBody @Valid PersonDto person, @PathVariable("id") int id) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        personService.delete(id);
    }
}
