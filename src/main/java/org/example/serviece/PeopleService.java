package org.example.serviece;

import org.example.dto.PersonDto;
import org.example.exception.PersonNotFoundException;
import org.example.mapper.PersonMapper;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.util.Examine;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    @Autowired
    public PeopleService(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PersonDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Person findById(final int id) {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public Person findByIdWithBooks(int id) {
        Person person = findById(id);
        Hibernate.initialize(person.getBooks()); // для подзагрузки книг. необязательно,
        // т.к. книги точно будут подзагружены - получение person и books в одной транзакции
        Examine.delayInReturningBook(person.getBooks());
        return person;
    }

    @Transactional
    public void save(Person person) {
        repository.save(person);
    }

    @Transactional
    public Person update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        return repository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Person> findByName(String name) {
        return repository.findByName(name);
    }

}
