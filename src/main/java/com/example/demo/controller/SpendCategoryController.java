package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Spend Category API")
public class SpendCategoryController {

    private final SpendCategoryService service;

    public SpendCategoryController(SpendCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SpendCategory> create(@RequestBody SpendCategory category) {
        return ResponseEntity.ok(service.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpendCategory> update(
            @PathVariable Long id,
            @RequestBody SpendCategory category) {
        return ResponseEntity.ok(service.updateCategory(id, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpendCategory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<SpendCategory>> getAll() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateCategory(id);
        return ResponseEntity.noContent().build();
    }
}
