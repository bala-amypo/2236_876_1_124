package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diversity")
public class DiversityClassificationController {

    private final DiversityClassificationService service;

    public DiversityClassificationController(DiversityClassificationService service) {
        this.service = service;
    }

    @PostMapping
    public DiversityClassification create(@RequestBody DiversityClassification entity) {
        return service.create(entity);
    }

    @GetMapping
    public List<DiversityClassification> getAll() {
        return service.getAll();
    }
}
