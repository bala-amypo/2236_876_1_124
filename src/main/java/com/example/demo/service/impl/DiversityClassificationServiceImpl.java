package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import java.util.List;

public class DiversityClassificationServiceImpl
        implements DiversityClassificationService {

    private final DiversityClassificationRepository repo;

    public DiversityClassificationServiceImpl(DiversityClassificationRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<DiversityClassification> getAll() {
        return repo.findAll();
    }

    @Override
    public void deactivateClassification(Long id) {
        DiversityClassification dc = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        dc.setActive(false);
        repo.save(dc);
    }
}
