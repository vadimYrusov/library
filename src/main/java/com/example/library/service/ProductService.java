package com.example.library.service;

import com.example.library.model.Product;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.ProductRepository;
import com.example.library.repository.PublishingRepository;
import com.example.library.service.interfaces.ProductServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepository productRepository;

    private final AuthorRepository authorRepository;

    private final PublishingRepository publishingRepository;

    public ProductService(ProductRepository productRepository, AuthorRepository authorRepository, PublishingRepository publishingRepository) {
        this.productRepository = productRepository;
        this.authorRepository = authorRepository;
        this.publishingRepository = publishingRepository;
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByAuthor(String name) {
        List products = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (authorRepository.findById(product.getAuthor().getId()).get().getName().toLowerCase().equals(name.toLowerCase())) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public List<Product> findByPublisher(String name) {
        List products = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (publishingRepository.findById(product.getPublishing().getId()).get().getPublishing_house_name().toLowerCase().equals(name.toLowerCase())) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public List<Product> findByCategory(String category) {
        List products = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (product.getCategory().toLowerCase().equals(category.toLowerCase())) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product create(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product update(Product product, Long id) {
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

    @Override
    public void delete(Long id) {
        productRepository.delete(productRepository.findById(id).get());
    }
}
