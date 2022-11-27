package com.example.library.model;

import org.springframework.data.annotation.Id;

public class Publishing {

    @Id
    private Long id;

    private String publishing_house_name;

    public Publishing(Long id, String publishingHouseName) {
        this.id = id;
        this.publishing_house_name = publishingHouseName;
    }

    public Publishing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublishing_house_name() {
        return publishing_house_name;
    }

    public void setPublishing_house_name(String publishing_house_name) {
        this.publishing_house_name = publishing_house_name;
    }

    @Override
    public String toString() {
        return "Publishing{" +
                "id=" + id +
                ", publishing_house_name='" + publishing_house_name + '\'' +
                '}';
    }
}
