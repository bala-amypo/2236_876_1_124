package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spend-categories")
public class SpendCategoryController {

    private final SpendCategoryService service;

    public SpendCategoryController(SpendCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public SpendCategory create(@RequestBody SpendCategory category) {
        return service.create(category);
    }

    @GetMapping("/{id}")
    public SpendCategory get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<SpendCategory> listActive() {
        return service.getActiveCategories();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }
}
