package com.example.library.controller;

import com.example.library.model.Publishing;
import com.example.library.repository.PublishingRepository;
import com.example.library.service.interfaces.PublishingServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/publishing")
public class PublishingController {

    private final PublishingRepository publishingRepository;

    private final PublishingServiceInterface publishingServiceInterface;

    public PublishingController(PublishingRepository publishingRepository, PublishingServiceInterface publishingServiceInterface) {
        this.publishingRepository = publishingRepository;
        this.publishingServiceInterface = publishingServiceInterface;
    }

    @GetMapping
    public Iterable<Publishing> findAll() {
        return publishingServiceInterface.findAll();
    }

    @GetMapping("/{id}")
    public Publishing findById(@PathVariable Long id) {
        if (publishingRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return publishingServiceInterface.findById(id);
    }

    @PostMapping("/create")
    public Publishing create(@RequestBody Publishing publishing) {
        if (publishing == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return publishingServiceInterface.create(publishing);
    }

    @PostMapping("/update/{id}")
    public Publishing update(@RequestBody Publishing publishing, @PathVariable Long id) {
        if (publishing == null || publishingRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return publishingServiceInterface.update(publishing, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (publishingRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        publishingServiceInterface.delete(id);
    }
}
