package com.example.demo.service;

import com.example.demo.entity.SpendCategory;
import java.util.List;
import java.util.Optional;

public interface SpendCategoryService {
    SpendCategory createSpendCategory(SpendCategory spendCategory);
    List<SpendCategory> getAllSpendCategories();
    Optional<SpendCategory> getSpendCategoryById(Long id);
    SpendCategory updateSpendCategory(Long id, SpendCategory spendCategory);
    void deleteSpendCategory(Long id);
}
