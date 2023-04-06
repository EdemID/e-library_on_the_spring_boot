package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.PersonDto;
import org.example.model.Person;
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
    public Person show(@PathVariable("id") int id) {
        return personService.findByIdWithBooks(id);
    }

    @PostMapping("/new")
    public void create(@RequestBody @Valid Person person) {
        personService.save(person);
    }

    @PatchMapping("/{id}/edit")
    public void edit(@PathVariable("id") int id, @Valid Person person) {
        personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        personService.delete(id);
    }
}
