package org.example.mapper;

import org.example.dto.PersonDto;
import org.example.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper implements CustomModelMapper {

    public Person toEntity(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    public PersonDto toDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    public List<PersonDto> toDtoList(List<Person> people) {
        return people.stream().map(this::toDto).collect(Collectors.toList());
    }
}
