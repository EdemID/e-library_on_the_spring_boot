package org.example.api.model.web;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

public interface WebController<T, I> {

    @GetMapping("/{id}")
    T show(@PathVariable("id") final I id);

    @PostMapping("/new")
    I create(@RequestBody @Valid final T dto);

    @PatchMapping("/{id}/edit")
    T edit(@RequestBody @Valid final T dto, @PathVariable("id") final I id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") final I id);
}
