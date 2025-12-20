package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classifications")
public class DiversityClassificationController {

    private final DiversityClassificationService service;

    public DiversityClassificationController(DiversityClassificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DiversityClassification> createClassification(@RequestBody DiversityClassification classification) {
        DiversityClassification created = service.createClassification(classification);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiversityClassification> getClassificationById(@PathVariable Long id) {
        DiversityClassification classification = service.getClassificationById(id);
        return ResponseEntity.ok(classification);
    }

    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAllClassifications() {
        return ResponseEntity.ok(service.getAllClassifications());
    }
}
