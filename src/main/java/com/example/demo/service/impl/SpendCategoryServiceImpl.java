package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {

    private final SpendCategoryRepository repository;

    public SpendCategoryServiceImpl(SpendCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SpendCategory createCategory(SpendCategory category) {
        return repository.save(category);
    }

    @Override
    public List<SpendCategory> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {
        SpendCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Spend category not found"));

        category.setActive(false);
        repository.save(category);
    }
}
