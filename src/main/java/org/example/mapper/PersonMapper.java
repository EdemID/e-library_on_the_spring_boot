package org.example.mapper;

import org.example.entity.Person;
import org.example.model.PersonDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper implements CustomModelMapper {

    public Person toEntity(final PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    public PersonDto toDto(final Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    public List<PersonDto> toDtoList(final List<Person> people) {
        return people.stream().map(this::toDto).collect(Collectors.toList());
    }
}
