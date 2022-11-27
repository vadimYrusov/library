package com.example.library.service.interfaces;

import com.example.library.model.Author;

public interface AuthorServiceInterface {

    Iterable<Author> findAll();

    Author findById(Long id);

    Author create(Author author);

    Author update(Author author, Long id);

    void delete(Long id);
}
