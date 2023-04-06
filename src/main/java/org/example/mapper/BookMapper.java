package org.example.mapper;

import org.example.dto.BookDto;
import org.example.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final ModelMapper modelMapper;

    public BookMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Book toEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public BookDto toDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }
}
