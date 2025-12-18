package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class SpendCategoryController {

    @Autowired
    private SpendCategoryService service;

    @PostMapping
    public SpendCategory create(@RequestBody SpendCategory category) {
        return service.createCategory(category);
    }

    @PutMapping("/{id}")
    public SpendCategory update(@PathVariable Long id,
                                @RequestBody SpendCategory category) {
        return service.updateCategory(id, category);
    }

    @GetMapping("/{id}")
    public SpendCategory getById(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @GetMapping
    public List<SpendCategory> getAll() {
        return service.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateCategory(id);
    }
}
