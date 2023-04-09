package org.example.service.impl;

import org.example.entity.Person;
import org.example.exception.PersonNotFoundException;
import org.example.mapper.PersonMapper;
import org.example.model.PersonDto;
import org.example.repository.PersonRepository;
import org.example.service.BusinessContract;
import org.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService implements PersonService, BusinessContract<PersonDto, Integer> {

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

    @Override
    public PersonDto findById(final Integer id) {
        return mapper.toDto(
                repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id))
        );
    }

    @Override
    public Integer save(final PersonDto person) {
        Person entity = mapper.toEntity(person);
        repository.save(entity);

        return entity.getId();
    }

    @Override
    public PersonDto update(final Integer id, final PersonDto updatedPerson) {
        Person personToBeUpdated = mapper.toEntity(updatedPerson);
        personToBeUpdated.setId(id);

        return mapper.toDto(repository.save(personToBeUpdated));
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }

    public List<Person> findByName(final String name) {
        return repository.findByName(name);
    }

}
