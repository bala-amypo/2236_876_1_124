package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // ✅ ensures Spring can inject this bean
public class DiversityTargetServiceImpl implements DiversityTargetService {

    private final DiversityTargetRepository repo;

    public DiversityTargetServiceImpl(DiversityTargetRepository repo) {
        this.repo = repo;
    }

    @Override
    public DiversityTarget create(DiversityTarget entity) {
        return repo.save(entity);
    }

    @Override
    public List<DiversityTarget> getAll() {
        return repo.findAll();
    }

    @Override
    public DiversityTarget getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("DiversityTarget not found with id: " + id));
    }

    @Override
    public DiversityTarget update(Long id, DiversityTarget entity) {
        DiversityTarget existing = getById(id);
        existing.setTargetName(entity.getTargetName());
        existing.setDescription(entity.getDescription());
        existing.setActive(entity.getActive());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
