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
    public List<SpendCategory> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public SpendCategory getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SpendCategory not found with id " + id));
    }

    @Override
    public SpendCategory createCategory(SpendCategory category) {
        return repository.save(category);
    }

    @Override
    public SpendCategory updateCategory(Long id, SpendCategory updatedCategory) {
        SpendCategory category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SpendCategory not found with id " + id));

        category.setName(updatedCategory.getName());
        category.setActive(updatedCategory.getActive());
        return repository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        SpendCategory category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SpendCategory not found with id " + id));
        repository.delete(category);
    }

    @Override
    public void deactivateCategory(Long id) {
        SpendCategory category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SpendCategory not found with id " + id));
        category.setActive(false);
        repository.save(category);
    }
}
