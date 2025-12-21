package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class SpendCategoryController {

    private final SpendCategoryService service;

    public SpendCategoryController(SpendCategoryService service) {
        this.service = service;
    }

    @Operation(summary = "Get all spend categories")
    @GetMapping
    public ResponseEntity<List<SpendCategory>> getAll() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @Operation(summary = "Deactivate category")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateCategory(id);
        return ResponseEntity.ok().build();
    }
}
