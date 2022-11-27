package com.example.library.controller;

import com.example.library.model.Publishing;
import com.example.library.repository.PublishingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/publishing")
public class PublishingController {

    private final PublishingRepository publishingRepository;

    public PublishingController(PublishingRepository publishingRepository) {
        this.publishingRepository = publishingRepository;
    }

    @GetMapping
    public Iterable<Publishing> findAll() {
        return publishingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publishing findById(@PathVariable Long id) {
        if (publishingRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return publishingRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Publishing create(@RequestBody Publishing publishing) {
        if (publishing == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        publishingRepository.save(publishing);
        return publishing;
    }

    @PostMapping("/update/{id}")
    public Publishing update(@RequestBody Publishing publishing, @PathVariable Long id) {
        if (publishing == null || publishingRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Publishing publishing1 = publishingRepository.findById(id).get();
        publishing1.setId(publishing.getId());
        publishing1.setPublishing_house_name(publishing.getPublishing_house_name());
        publishingRepository.save(publishing1);
        return publishing1;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (publishingRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        publishingRepository.delete(publishingRepository.findById(id).get());

    }
}
