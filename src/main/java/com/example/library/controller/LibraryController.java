package com.example.library.controller;

import com.example.library.model.Product;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.ProductRepository;
import com.example.library.repository.PublishingRepository;
import com.example.library.service.interfaces.ProductServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final ProductServiceInterface productServiceInterface;

    private final ProductRepository productRepository;

    public LibraryController(ProductServiceInterface productServiceInterface, ProductRepository productRepository, AuthorRepository authorRepository, PublishingRepository publishingRepository) {
        this.productServiceInterface = productServiceInterface;
        this.productRepository = productRepository;
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productServiceInterface.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        if (productRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return productServiceInterface.findById(id);
    }

    @GetMapping("author/{name}")
    public List<Product> findByAuthor(@PathVariable String name) {
        if (name.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return productServiceInterface.findByAuthor(name);
    }

    @GetMapping("publisher/{name}")
    public List<Product> findByPublisher(@PathVariable String name) {
        if (name.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return productServiceInterface.findByPublisher(name);
    }

    @GetMapping("category/{category}")
    public List<Product> findByCategory(@PathVariable String category) {
        if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return productServiceInterface.findByCategory(category);
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        if (product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return productServiceInterface.create(product);
    }

    @PostMapping("/update/{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id) {
        if (product == null || productRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return productServiceInterface.update(product, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (productRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        productServiceInterface.delete(id);
    }
}
