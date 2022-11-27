package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;

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

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AggregateReference<Publishing, Long> getPublishing() {
        return publishing;
    }

    public void setPublishing(AggregateReference<Publishing, Long> publishing) {
        this.publishing = publishing;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public AggregateReference<Author, Long> getAuthor() {
        return author;
    }

    public void setAuthor(AggregateReference<Author, Long> author) {
        this.author = author;
    }

    public LocalDateTime getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDateTime release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", publishing=" + publishing +
                ", theme='" + theme + '\'' +
                ", edition='" + edition + '\'' +
                ", author=" + author +
                ", release_date=" + release_date +
                '}';
    }
}
