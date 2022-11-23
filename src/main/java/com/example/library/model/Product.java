package com.example.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Product {

    @Id
    private Long id;

    private String title;

    private String category;

//    private String publishing;

    private String theme;

    private String edition;

//    private String author;

    private LocalDateTime release_date;

    public Product(String title, String category, String theme, String edition) {
        this.title = title;
        this.category = category;
//        this.publishing = publishing;
        this.theme = theme;
        this.edition = edition;
//        this.author = author;
        this.release_date = LocalDateTime.now();
    }
}
