package com.example.demo.service;

import com.example.demo.entity.SpendCategory;

import java.util.List;

public interface SpendCategoryService {

    SpendCategory create(SpendCategory category);

    SpendCategory getById(Long id);

    List<SpendCategory> getActiveCategories();

    void deactivate(Long id);
}
