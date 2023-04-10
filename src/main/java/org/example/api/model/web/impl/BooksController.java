package org.example.api.model.web.impl;

import org.example.api.model.web.BookController;
import org.example.model.BookDto;
import org.example.service.impl.BooksService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BooksController implements BookController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    public List<BookDto> index(
            final Integer page,
            final Integer booksPerPage,
            final boolean sortByYear
    ) {
        return booksService.findAll(page, booksPerPage, sortByYear);
    }

    public BookDto show(final Integer id) {
        return booksService.findById(id);
    }

    public Integer create(final BookDto book) {
        return booksService.save(book);
    }

    public BookDto edit(final BookDto book, final Integer id) {
        return booksService.update(id, book);
    }

    public void delete(final Integer id) {
        booksService.delete(id);
    }

    public BookDto assignBook(final Integer personId, final Integer bookId) {
        return booksService.assign(bookId, personId);
    }

    public BookDto returnBook(final Integer id) {
        return booksService.returnBook(id);
    }

    public List<BookDto> makeSearch(final String searchTerm) {
        return booksService.findByBookNameStartsWith(searchTerm);
    }
}
