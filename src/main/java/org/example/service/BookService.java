package org.example.service;

import org.example.model.BookDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookService {

    List<BookDto> findAll(final Integer page, final int booksPerPage, final boolean isSortByYear);

    List<BookDto> findByBookNameStartsWith(final String startsWith);

    @Transactional
    BookDto assign(final int bookId, final int personId);

    @Transactional
    BookDto returnBook(final int id);
}
