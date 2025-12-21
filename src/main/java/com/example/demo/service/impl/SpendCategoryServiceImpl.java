package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {

    private final SpendCategoryRepository spendCategoryRepository;

    @Autowired
    public SpendCategoryServiceImpl(SpendCategoryRepository spendCategoryRepository) {
        this.spendCategoryRepository = spendCategoryRepository;
    }

    @Override
    public SpendCategory createSpendCategory(SpendCategory spendCategory) {
        return spendCategoryRepository.save(spendCategory);
    }

    @Override
    public List<SpendCategory> getAllSpendCategories() {
        return spendCategoryRepository.findAll();
    }

    @Override
    public Optional<SpendCategory> getSpendCategoryById(Long id) {
        return spendCategoryRepository.findById(id);
    }

    @Override
    public SpendCategory updateSpendCategory(Long id, SpendCategory spendCategory) {
        return spendCategoryRepository.findById(id).map(existing -> {
            existing.setName(spendCategory.getName());
            existing.setDescription(spendCategory.getDescription());
            return spendCategoryRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("SpendCategory not found with id " + id));
    }

    @Override
    public void deleteSpendCategory(Long id) {
        spendCategoryRepository.deleteById(id);
    }
}
