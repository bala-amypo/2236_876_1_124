package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class SpendCategoryController {

    private final SpendCategoryService categoryService;

    public SpendCategoryController(SpendCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public SpendCategory createCategory(@RequestBody SpendCategory category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public SpendCategory getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<SpendCategory> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}/deactivate")
    public SpendCategory deactivateCategory(@PathVariable Long id) {
        categoryService.deactivateCategory(id);
        return categoryService.getCategoryById(id);
    }
}
