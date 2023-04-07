package org.example.mapper;

import org.example.dto.BookDto;
import org.example.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper implements CustomModelMapper {

    public Book toEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public BookDto toDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public List<BookDto> toDtoLists(List<Book> books) {
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }
}
