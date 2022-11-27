package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.model.Product;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.interfaces.AuthorServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements AuthorServiceInterface {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author create(Author author) {
        authorRepository.save(author);
        return author;
    }

    @Override
    public Author update(Author author, Long id) {
        Author author1 = authorRepository.findById(id).get();
        author1.setId(author.getId());
        author1.setName(author.getName());
        author1.setSurname(author.getSurname());
        authorRepository.save(author1);
        return author1;
    }

    @Override
    public void delete(Long id) {
        authorRepository.delete(authorRepository.findById(id).get());
    }
}
