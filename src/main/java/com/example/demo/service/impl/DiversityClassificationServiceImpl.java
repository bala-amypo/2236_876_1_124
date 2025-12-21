package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // ✅ Important: tells Spring to create a bean
public class DiversityClassificationServiceImpl implements DiversityClassificationService {

    private final DiversityClassificationRepository repo;

    public DiversityClassificationServiceImpl(DiversityClassificationRepository repo) {
        this.repo = repo;
    }

    @Override
    public DiversityClassification create(DiversityClassification entity) {
        return repo.save(entity);
    }

    @Override
    public List<DiversityClassification> getAll() {
        return repo.findAll();
    }

    @Override
    public DiversityClassification getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public DiversityClassification update(Long id, DiversityClassification entity) {
        DiversityClassification existing = getById(id);
        existing.setName(entity.getName());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
