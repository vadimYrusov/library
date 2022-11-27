package com.example.library.service.interfaces;

import com.example.library.model.Publishing;

public interface PublishingServiceInterface {

    Iterable<Publishing> findAll();

    Publishing findById(Long id);

    Publishing create(Publishing publishing);

    Publishing update(Publishing publishing, Long id);

    void delete(Long id);
}
