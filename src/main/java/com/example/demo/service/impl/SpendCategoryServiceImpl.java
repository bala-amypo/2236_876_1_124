package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;

import java.util.List;

public class SpendCategoryServiceImpl
        implements SpendCategoryService {

    private final SpendCategoryRepository repository;

    public SpendCategoryServiceImpl(
            SpendCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SpendCategory create(SpendCategory category) {
        return repository.save(category);
    }

    @Override
    public SpendCategory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));
    }

    @Override
    public List<SpendCategory> getActiveCategories() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public void deactivate(Long id) {
        SpendCategory category = getById(id);
        category.setIsActive(false);
        repository.save(category);
    }
}
