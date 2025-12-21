package com.example.demo.service;

import com.example.demo.entity.DiversityTarget;
import java.util.List;

public interface DiversityTargetService {
    DiversityTarget create(DiversityTarget entity);
    List<DiversityTarget> getAll();
    DiversityTarget getById(Long id);
    DiversityTarget update(Long id, DiversityTarget entity);
    void delete(Long id);
}
