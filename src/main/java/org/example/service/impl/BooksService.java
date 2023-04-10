package org.example.service.impl;

import org.example.entity.Book;
import org.example.exception.BookNotFoundException;
import org.example.exception.IncorrectParametersException;
import org.example.mapper.BookMapper;
import org.example.model.BookDto;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.example.service.BusinessContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService implements BookService, BusinessContract<BookDto, Integer> {

    private final PeopleService peopleService;
    private final BookRepository repository;
    private final BookMapper bookMapper;

    @Autowired
    public BooksService(PeopleService peopleService, final BookRepository repository, final BookMapper bookMapper) {
        this.peopleService = peopleService;
        this.repository = repository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> findAll(final Integer page, final int booksPerPage, final boolean isSortByYear) throws IncorrectParametersException {
        List<Book> books;
        if (page == 0 && booksPerPage == 0) {
            books = findAll(isSortByYear);
        } else if (isSortByYear) {
            books = repository
                    .findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfPublication", "bookName")))
                    .getContent();
        } else {
            books = repository
                    .findAll(PageRequest.of(page, booksPerPage))
                    .getContent();
        }
        return bookMapper.toDtoLists(books);
    }

    private List<Book> findAll(final boolean isSortByYear) {
        if (isSortByYear) {
            return repository.findAll(Sort.by("yearOfPublication", "bookName"));
        }
        return repository.findAll();
    }

    public List<BookDto> findByBookNameStartsWith(final String startsWith) {
        if (startsWith.equals("")) {
            throw new BookNotFoundException(String.format("Book starts with %s not found", startsWith));
        } else {
            return bookMapper.toDtoLists(repository.findByBookNameStartsWith(startsWith));
        }
    }

    public BookDto assign(final int bookId, final int personId) {
        BookDto dto = findById(bookId);
        dto.setOwner(peopleService.findById(personId));
        dto.setTakenAt(new Date());

        Book entity = bookMapper.toEntity(dto);
        entity.setId(bookId);
        repository.save(entity);

        return dto;
    }

    public BookDto returnBook(final int id) {
        Book entity = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        entity.setOwner(null);
        entity.setTakenAt(null);

        BookDto book = bookMapper.toDto(entity);
        book.setExpired(false);

        return book;
    }

    @Override
    public BookDto findById(final Integer id) {
        Optional<Book> foundBook = repository.findById(id);
        return bookMapper.toDto(foundBook.orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public Integer save(final BookDto book) {
        Book entity = bookMapper.toEntity(book);
        repository.save(entity);

        return entity.getId();
    }

    @Override
    public BookDto update(final Integer id, final BookDto updatedBook) {
        Book bookToBeUpdated = bookMapper.toEntity(updatedBook);
        bookToBeUpdated.setId(id);
        bookToBeUpdated.setOwner(bookToBeUpdated.getOwner());

        return bookMapper.toDto(repository.save(bookToBeUpdated));
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }
}
