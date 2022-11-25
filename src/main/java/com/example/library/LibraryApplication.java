package com.example.library;

import com.example.library.model.Author;
import com.example.library.model.Product;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository products, AuthorRepository authors) {
		return args -> {
			AggregateReference<Author, Long> john = AggregateReference.to(authors.save(new Author(null, "John", "Tolkien")).getId());
			products.save(new Product("Lord of the ring", "book", "fantasy", "Standard", john));
		};
	}
}
