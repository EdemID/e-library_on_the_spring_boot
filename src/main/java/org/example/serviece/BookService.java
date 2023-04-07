package org.example.serviece;

import org.example.dto.BookDto;
import org.example.exception.BookNotFoundException;
import org.example.exception.IncorrectParametersException;
import org.example.mapper.BookMapper;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final PeopleService peopleService;
    private final BookRepository repository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(PeopleService peopleService, final BookRepository repository, final BookMapper bookMapper) {
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

    public BookDto findById(final int id) {
        Optional<Book> foundBook = repository.findById(id);
        return bookMapper.toDto(foundBook.orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Transactional
    public int save(final Book book) {
        repository.save(book);

        return book.getId();
    }

    @Transactional
    public BookDto update(final int id, final Book updatedBook) {
        Book bookToBeUpdated = repository.findById(id).get();

        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner()); // чтобы не терялась связь при обновлении
        return bookMapper.toDto(repository.save(updatedBook));
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public BookDto assign(int bookId, int personId) {
        BookDto dto = findById(bookId);

        dto.setOwner(peopleService.findById(personId));
        dto.setTakenAt(new Date());

        Book entity = bookMapper.toEntity(dto);
        entity.setId(bookId);
        save(entity);

        return dto;
    }

    @Transactional
    public BookDto returnBook(int id) {
        Book book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setOwner(null);
        book.setTakenAt(null);
        book.setExpired(false);
        return bookMapper.toDto(book);
//  save(book); - не нужен, так как book лежит в persistence context и Hibernate знает об этом book, поэтому сам обновит сущность в бд при каждом вызове setter
    }
}
