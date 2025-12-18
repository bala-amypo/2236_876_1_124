package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiversityTargetServiceImpl implements DiversityTargetService {

    @Autowired
    private DiversityTargetRepository repository;

    @Override
    public DiversityTarget createTarget(DiversityTarget target) {
        return repository.save(target);
    }

    @Override
    public DiversityTarget updateTarget(Long id, DiversityTarget target) {
        DiversityTarget existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        target.setId(existing.getId());
        return repository.save(target);
    }

    @Override
    public List<DiversityTarget> getAllTargets() {
        return repository.findAll();
    }

    @Override
    public List<DiversityTarget> getTargetsByYear(Integer year) {
        return repository.findByTargetYear(year);
    }

    @Override
    public void deactivateTarget(Long id) {
        DiversityTarget target = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
        target.setActive(false);
        repository.save(target);
    }
}
