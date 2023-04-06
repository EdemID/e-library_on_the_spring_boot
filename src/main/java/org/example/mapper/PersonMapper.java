package org.example.mapper;

import org.example.dto.PersonDto;
import org.example.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final ModelMapper modelMapper;

    public PersonMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Person toEntity(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    public PersonDto toDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }
}
