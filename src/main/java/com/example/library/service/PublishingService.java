package com.example.library.service;

import com.example.library.model.Publishing;
import com.example.library.repository.PublishingRepository;
import com.example.library.service.interfaces.PublishingServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class PublishingService implements PublishingServiceInterface {

    private final PublishingRepository publishingRepository;

    public PublishingService(PublishingRepository publishingRepository) {
        this.publishingRepository = publishingRepository;
    }

    @Override
    public Iterable<Publishing> findAll() {
        return publishingRepository.findAll();
    }

    @Override
    public Publishing findById(Long id) {
        return publishingRepository.findById(id).orElse(null);
    }

    @Override
    public Publishing create(Publishing publishing) {
        publishingRepository.save(publishing);
        return publishing;
    }

    @Override
    public Publishing update(Publishing publishing, Long id) {
        Publishing publishing1 = publishingRepository.findById(id).get();
        publishing1.setId(publishing.getId());
        publishing1.setPublishing_house_name(publishing.getPublishing_house_name());
        publishingRepository.save(publishing1);
        return publishing1;
    }

    @Override
    public void delete(Long id) {
        publishingRepository.delete(publishingRepository.findById(id).get());
    }
}
