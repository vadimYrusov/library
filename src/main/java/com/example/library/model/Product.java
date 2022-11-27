package com.example.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;

@Data
public class Product {

    @Id
    private Long id;

    private String title;

    private String category;

    private AggregateReference<Publishing, Long> publishing;

    private String theme;

    private String edition;

    private AggregateReference<Author, Long> author;

    private LocalDateTime release_date;

    public Product(String title, String category, String theme, String edition, AggregateReference<Author, Long> author, AggregateReference<Publishing, Long> publishing) {
        this.title = title;
        this.category = category;
        this.theme = theme;
        this.edition = edition;
        this.release_date = LocalDateTime.now();
        this.author = author;
        this.publishing = publishing;
    }
}
