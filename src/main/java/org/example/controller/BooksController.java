package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.BookDto;
import org.example.model.Book;
import org.example.serviece.BookService;
import org.example.serviece.PeopleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
// получать DTO
public class BooksController {

    private final BookService bookService;
    private final PeopleService personService;

    public BooksController(BookService bookService, PeopleService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public List<BookDto> index(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "0", name = "books_per_page", required = false) int booksPerPage,
            @RequestParam(name = "sort_by_year", required = false) boolean sortByYear
    ) {
        return bookService.findAll(page, booksPerPage, sortByYear);
    }

    @GetMapping("/{id}")
    public BookDto show(@PathVariable("id") int id) {
        return bookService.findById(id);
    }

    // TODO: Вернуть id
    @PostMapping("/new")
    public int create(@RequestBody @Valid Book book) {
        return bookService.save(book);
    }

    @PatchMapping("/{id}/edit")
    public BookDto edit(@RequestBody @Valid Book book, @PathVariable("id") int id) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        bookService.delete(id);
    }

    @PutMapping("/{id}/add")
    public BookDto assignBook(@RequestBody int personId, @PathVariable("id") int bookId) {
        return bookService.assign(bookId, personId);
    }

    @GetMapping("/{id}/return")
    public BookDto returnBook(@PathVariable("id") int id) {
        return bookService.returnBook(id);
    }

    @PostMapping("/search")
    public List<BookDto> makeSearch(@RequestBody String searchTerm) {
        return bookService.findByBookNameStartsWith(searchTerm);
    }
}
