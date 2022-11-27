package com.example.library.service.interfaces;

import com.example.library.model.Product;

import java.util.List;

public interface ProductServiceInterface {

    Iterable<Product> findAll();

    Product findById(Long id);

    List<Product> findByAuthor(String name);

    List<Product> findByPublisher(String name);

    List<Product> findByCategory(String category);

    Product create(Product product);

    Product update(Product product, Long id);

    void delete(Long id);
}

