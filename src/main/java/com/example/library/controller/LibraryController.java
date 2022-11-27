package com.example.library.controller;

import com.example.library.model.Product;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.ProductRepository;
import com.example.library.repository.PublishingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final ProductRepository productRepository;

    private final AuthorRepository authorRepository;

    private final PublishingRepository publishingRepository;

    public LibraryController(ProductRepository productRepository, AuthorRepository authorRepository, PublishingRepository publishingRepository) {
        this.productRepository = productRepository;
        this.authorRepository = authorRepository;
        this.publishingRepository = publishingRepository;
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        if (productRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("author/{name}")
    public List<Product> sortByAuthor(@PathVariable String name) {
        if (name.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List products = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (authorRepository.findById(product.getAuthor().getId()).get().getName().equals(name)) {
                products.add(product);
            }
        }
        return products;
    }

    @GetMapping("publisher/{name}")
    public List<Product> sortByPublisher(@PathVariable String name) {
        if (name.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List products = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (publishingRepository.findById(product.getPublishing().getId()).get().getPublishing_house_name().equals(name)) {
                products.add(product);
            }
        }
        return products;
    }

    @GetMapping("category/{category}")
    public List<Product> sortByCategory(@PathVariable String category) {
        if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List products = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (product.getCategory().equals(category)) {
                products.add(product);
            }
        }
        return products;
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        if (product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        productRepository.save(product);
        return product;
    }

    @PostMapping("/update/{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id) {
        if (product == null || productRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Product product1 = productRepository.findById(id).get();
        product1.setId(product.getId());
        product1.setTitle(product.getTitle());
        product1.setCategory(product.getCategory());
        product1.setPublishing(product.getPublishing());
        product1.setTheme(product.getTheme());
        product1.setEdition(product.getEdition());
        product1.setAuthor(product.getAuthor());
        product1.setRelease_date(product.getRelease_date());
        productRepository.save(product1);
        return product1;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (productRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        productRepository.delete(productRepository.findById(id).get());

    }
}
