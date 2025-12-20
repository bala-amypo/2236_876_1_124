package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spend-categories")
public class SpendCategoryController {

    private final SpendCategoryService service;

    public SpendCategoryController(SpendCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SpendCategory> create(@RequestBody SpendCategory category) {
        return ResponseEntity.ok(service.create(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpendCategory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getActiveById(id));
    }

    @GetMapping
    public ResponseEntity<List<SpendCategory>> getAllActive() {
        return ResponseEntity.ok(service.getAllActive());
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<SpendCategory> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(service.deactivateCategory(id));
    }
}
