package org.example.api.model.web;

import org.example.model.BookDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookController extends WebController<BookDto, Integer> {

    @GetMapping
    List<BookDto> index(
            @RequestParam(defaultValue = "0", required = false) final Integer page,
            @RequestParam(defaultValue = "0", name = "books_per_page", required = false) final Integer booksPerPage,
            @RequestParam(name = "sort_by_year", required = false) final boolean sortByYear
    );

    @PutMapping("/{id}/add")
    BookDto assignBook(@RequestBody final Integer personId, @PathVariable("id") final Integer bookId);

    @GetMapping("/{id}/return")
    BookDto returnBook(@PathVariable("id") final Integer id);

    @PostMapping("/search")
    List<BookDto> makeSearch(@RequestBody final String searchTerm);
}
