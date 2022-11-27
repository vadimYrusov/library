package com.example.library.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Publishing {

    @Id
    private Long id;

    private String publishing_house_name;

    public Publishing(Long id, String publishingHouseName) {
        this.id = id;
        this.publishing_house_name = publishingHouseName;
    }
}
