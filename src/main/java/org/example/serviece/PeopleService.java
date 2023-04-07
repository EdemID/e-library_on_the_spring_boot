package org.example.serviece;

import org.example.dto.PersonDto;
import org.example.exception.PersonNotFoundException;
import org.example.mapper.PersonMapper;
import org.example.model.Book;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.util.Examine;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return mapper.toDtoList(repository.findAll());
    }

    public Person findById(final int id) {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public Person findByIdWithBooks(int id) {
        Person person = findById(id);
        Hibernate.initialize(person.getBooks()); // для подзагрузки книг. необязательно,
        // т.к. книги точно будут подзагружены - получение person и books в одной транзакции
        // проверяем книги полльзователя на истекание срока
        for (Book book : person.getBooks()) {
            Examine.bookExpire(book);
        }
        return person;
    }

    @Transactional
    public int save(Person person) {
        repository.save(person);
        return person.getId();
    }

    @Transactional
    public PersonDto update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        return mapper.toDto(repository.save(updatedPerson));
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Person> findByName(String name) {
        return repository.findByName(name);
    }

}
