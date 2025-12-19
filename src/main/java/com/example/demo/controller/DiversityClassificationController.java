package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classifications")
public class DiversityClassificationController {

    private final DiversityClassificationService classificationService;

    public DiversityClassificationController(DiversityClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @PostMapping
    public DiversityClassification createClassification(@RequestBody DiversityClassification dc) {
        return classificationService.createClassification(dc);
    }

    @GetMapping("/{id}")
    public DiversityClassification getClassification(@PathVariable Long id) {
        return classificationService.getClassificationById(id);
    }

    @GetMapping
    public List<DiversityClassification> getAllClassifications() {
        return classificationService.getAllClassifications();
    }

    @PutMapping("/{id}/deactivate")
    public DiversityClassification deactivateClassification(@PathVariable Long id) {
        classificationService.deactivateClassification(id);
        return classificationService.getClassificationById(id);
    }
}
