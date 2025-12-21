package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classifications")
public class DiversityClassificationController {

    private final DiversityClassificationService service;

    public DiversityClassificationController(DiversityClassificationService service) {
        this.service = service;
    }

    @Operation(summary = "Get all classifications")
    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAll() {
        return ResponseEntity.ok(service.getAllClassifications());
    }

    @Operation(summary = "Deactivate a classification")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateClassification(id);
        return ResponseEntity.ok().build();
    }
}
