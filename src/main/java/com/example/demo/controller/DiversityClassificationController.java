package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classifications")
@Tag(name = "Diversity Classification API")
public class DiversityClassificationController {

    private final DiversityClassificationService service;

    public DiversityClassificationController(DiversityClassificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DiversityClassification> create(@RequestBody DiversityClassification c) {
        return ResponseEntity.ok(service.createClassification(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiversityClassification> update(
            @PathVariable Long id,
            @RequestBody DiversityClassification c) {
        return ResponseEntity.ok(service.updateClassification(id, c));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiversityClassification> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAll() {
        return ResponseEntity.ok(service.getAllClassifications());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateClassification(id);
        return ResponseEntity.noContent().build();
    }
}