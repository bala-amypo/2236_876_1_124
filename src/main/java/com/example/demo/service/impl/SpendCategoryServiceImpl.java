package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import java.util.List;

public class SpendCategoryServiceImpl implements SpendCategoryService {

    private final SpendCategoryRepository repo;

    public SpendCategoryServiceImpl(SpendCategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<SpendCategory> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {
        SpendCategory sc = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        sc.setActive(false);
        repo.save(sc);
    }
}
