package com.example.demo.service;

import com.example.demo.entity.SpendCategory;

import java.util.List;

public interface SpendCategoryService {

    List<SpendCategory> getAllCategories();

    SpendCategory getCategoryById(Long id);

    SpendCategory createCategory(SpendCategory category);

    SpendCategory updateCategory(Long id, SpendCategory category);

    void deleteCategory(Long id);

    void deactivateCategory(Long id);
}
