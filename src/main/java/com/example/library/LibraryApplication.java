package com.example.library;

import com.example.library.model.Author;
import com.example.library.model.Product;
import com.example.library.model.Publishing;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.ProductRepository;
import com.example.library.repository.PublishingRepository;
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
	CommandLineRunner commandLineRunner(ProductRepository products, AuthorRepository authors, PublishingRepository publishers) {
		return args -> {
			AggregateReference<Author, Long> john = AggregateReference.to(authors.save(new Author(null, "John", "Tolkien")).getId());
			AggregateReference<Author, Long> jack = AggregateReference.to(authors.save(new Author(null, "Jack", "London")).getId());
			AggregateReference<Author, Long> ernest = AggregateReference.to(authors.save(new Author(null, "Ernest ", "Hemingway")).getId());
			AggregateReference<Publishing, Long> fan = AggregateReference.to(publishers.save(new Publishing(null, "FantasyBooks")).getId());
			AggregateReference<Publishing, Long> classics = AggregateReference.to(publishers.save(new Publishing(null, "ClassicsGroup")).getId());
			AggregateReference<Publishing, Long> great = AggregateReference.to(publishers.save(new Publishing(null, "Great-Authors")).getId());
			products.save(new Product("Lord of the ring", "book", "fantasy", "Standard", john, fan));
			products.save(new Product("Martin Eden", "book", " Novel", "Standard", jack, classics));
			products.save(new Product("Jack London: The Call of the Wild", "book", "Adventure fiction", "Standard", jack, great));
			products.save(new Product("The Sun Also Rises", "book", "Historical Fiction", "Standard", ernest, classics));
			products.save(new Product("The Old Man and the Sea", "book", "Novel", "Standard", ernest, great));
		};
	}
}
