package org.example.mapper;

import org.example.entity.Book;
import org.example.model.BookDto;
import org.example.util.Examine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper implements CustomModelMapper {

    public Book toEntity(final BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public BookDto toDto(final Book entity) {
        BookDto book = modelMapper.map(entity, BookDto.class);
        if (book.getTakenAt() != null)
            Examine.bookExpire(book);
        return book;
    }

    public List<BookDto> toDtoLists(List<Book> books) {
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }
}
