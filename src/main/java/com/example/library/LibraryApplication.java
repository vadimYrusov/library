package com.example.library;

import com.example.library.model.Product;
import com.example.library.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository products) {
		return args -> {
			products.save(new Product("Lord of the ring", "book", "fantasy", "Standard"));
		};
	}
}
