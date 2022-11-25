package com.example.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Author {

    @Id
    private Long id;

    private String name;

    private String surname;

    public Author(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
