package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.Person;
import org.example.serviece.BookService;
import org.example.serviece.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService personService;
    private final BookService bookService;

    @Autowired
    public PeopleController(PeopleService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping()
    public void index() {
//        model.addAttribute("people", personService.findAll());
//        return "people/index";
    }

    @GetMapping("/{id}")
    public void show(@PathVariable("id") int id) {
//        model.addAttribute("person", personService.findByIdWithBooks(id));

//        return "people/show";
    }

    @GetMapping("/new")
    public void newPerson(@ModelAttribute("person") Person person) {
//        return "people/new";
    }

    @PostMapping()
    public void create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
//            return "people/new";

        personService.save(person);
//        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public void edit(@PathVariable("id") int id) {
//        model.addAttribute("person", personService.findById(id));
//        return "people/edit";
    }

    @PatchMapping("/{id}")
    public void update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
//            return "people/edit";

        personService.update(id, person);
//        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        personService.delete(id);
//        return "redirect:/people";
    }
}
