package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.interfaces.AuthorServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorServiceInterface authorServiceInterface;

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorServiceInterface authorServiceInterface, AuthorRepository authorRepository) {
        this.authorServiceInterface = authorServiceInterface;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public Iterable<Author> findAll() {
        return authorServiceInterface.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id) {
        if (authorRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return authorServiceInterface.findById(id);
    }

    @PostMapping("/create")
    public Author create(@RequestBody Author author) {
        if (author == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return authorServiceInterface.create(author);
    }

    @PostMapping("/update/{id}")
    public Author update(@RequestBody Author author, @PathVariable Long id) {
        if (author == null || authorRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return authorServiceInterface.update(author, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (authorRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        authorServiceInterface.delete(id);
    }
}
