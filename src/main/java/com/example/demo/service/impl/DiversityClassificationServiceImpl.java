package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;

import java.util.List;

public class DiversityClassificationServiceImpl
        implements DiversityClassificationService {

    private final DiversityClassificationRepository repository;

    public DiversityClassificationServiceImpl(
            DiversityClassificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiversityClassification create(DiversityClassification classification) {
        return repository.save(classification);
    }

    @Override
    public DiversityClassification getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Classification not found"));
    }

    @Override
    public List<DiversityClassification> getAllActive() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public void deactivate(Long id) {
        DiversityClassification classification = getById(id);
        classification.setIsActive(false);
        repository.save(classification);
    }
}
