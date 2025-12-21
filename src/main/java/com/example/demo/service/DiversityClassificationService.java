package com.example.demo.service;

import com.example.demo.entity.DiversityClassification;
import java.util.List;

public interface DiversityClassificationService {
    DiversityClassification create(DiversityClassification entity);
    List<DiversityClassification> getAll();
    DiversityClassification getById(Long id);
    DiversityClassification update(Long id, DiversityClassification entity);
    void delete(Long id);
}
