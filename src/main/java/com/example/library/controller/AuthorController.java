package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id) {
        if (authorRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return authorRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Author create(@RequestBody Author author) {
        if (author == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        authorRepository.save(author);
        return author;
    }

    @PostMapping("/update/{id}")
    public Author update(@RequestBody Author author, @PathVariable Long id) {
        if (author == null || authorRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Author author1 = authorRepository.findById(id).get();
        author1.setId(author.getId());
        author1.setName(author.getName());
        author1.setSurname(author.getSurname());
        authorRepository.save(author1);
        return author1;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (authorRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        authorRepository.delete(authorRepository.findById(id).get());

    }
}
