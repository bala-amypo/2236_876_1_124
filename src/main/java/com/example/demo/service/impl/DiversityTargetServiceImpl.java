package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import java.util.List;

public class DiversityTargetServiceImpl implements DiversityTargetService {

    private final DiversityTargetRepository repository;

    public DiversityTargetServiceImpl(DiversityTargetRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiversityTarget createTarget(DiversityTarget target) {
        if (target.getTargetPercentage() < 0 || target.getTargetPercentage() > 100) {
            throw new BadRequestException("Percentage must be between 0 and 100");
        }
        return repository.save(target);
    }

    @Override
    public List<DiversityTarget> getTargetsByYear(int year) {
        return repository.findByTargetYear(year);
    }

    @Override
    public List<DiversityTarget> getAllTargets() {
        return repository.findAll();
    }

    @Override
    public void deactivateTarget(Long id) {
        DiversityTarget t = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        t.setActive(false);
        repository.save(t);
    }
}
