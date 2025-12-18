package com.example.demo.service;

import com.example.demo.entity.DiversityClassification;
import java.util.List;

public interface DiversityClassificationService {
    DiversityClassification createClassification(DiversityClassification c);
    DiversityClassification updateClassification(Long id, DiversityClassification c);
    List<DiversityClassification> getAllClassifications();
    DiversityClassification getById(Long id);
    void deactivateClassification(Long id);
}
