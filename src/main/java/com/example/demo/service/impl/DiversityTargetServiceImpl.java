package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;

import java.util.List;

public class DiversityTargetServiceImpl implements DiversityTargetService {

    private final DiversityTargetRepository repo;

    public DiversityTargetServiceImpl(DiversityTargetRepository repo) {
        this.repo = repo;
    }

    @Override
    public DiversityTarget createTarget(DiversityTarget target) {
        return repo.save(target);
    }

    @Override
    public List<DiversityTarget> getTargetsByYear(int year) {
        return repo.findByTargetYear(year);
    }

    @Override
    public List<DiversityTarget> getAllTargets() {
        return repo.findAll();
    }

    @Override
    public void deactivateTarget(Long id) {
        DiversityTarget t = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        t.setActive(false);
        repo.save(t);
    }
}
