package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.BadRequestException;
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
    public SpendCategory create(SpendCategory category) {
        if (repository.existsByCode(category.getCode())) {
            throw new BadRequestException("Spend category code already exists");
        }
        if (repository.existsByName(category.getName())) {
            throw new BadRequestException("Spend category name already exists");
        }
        category.setActive(true);
        return repository.save(category);
    }

    @Override
    public SpendCategory getActiveById(Long id) {
        return repository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spend category not found"));
    }

    @Override
    public List<SpendCategory> getAllActive() {
        return repository.findByActiveTrue();
    }

    @Override
    public SpendCategory deactivateCategory(Long id) {
        SpendCategory category = getActiveById(id);
        category.setActive(false);
        return repository.save(category);
    }

    @Override
    public List<SpendCategory> getAllCategories() {
        return repository.findAll();
    }
}
