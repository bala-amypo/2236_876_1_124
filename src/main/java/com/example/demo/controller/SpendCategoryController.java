package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spend-categories")
public class SpendCategoryController {

    private final SpendCategoryService spendCategoryService;

    public SpendCategoryController(SpendCategoryService spendCategoryService) {
        this.spendCategoryService = spendCategoryService;
    }

    @PostMapping
    public ResponseEntity<SpendCategory> create(@RequestBody SpendCategory spendCategory) {
        return ResponseEntity.ok(spendCategoryService.createSpendCategory(spendCategory));
    }

    @GetMapping
    public ResponseEntity<List<SpendCategory>> getAll() {
        return ResponseEntity.ok(spendCategoryService.getAllSpendCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpendCategory> getById(@PathVariable Long id) {
        return spendCategoryService.getSpendCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpendCategory> update(@PathVariable Long id, @RequestBody SpendCategory spendCategory) {
        return ResponseEntity.ok(spendCategoryService.updateSpendCategory(id, spendCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        spendCategoryService.deleteSpendCategory(id);
        return ResponseEntity.noContent().build();
    }
}
