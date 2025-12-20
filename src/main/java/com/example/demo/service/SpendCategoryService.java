package com.example.demo.service;

import com.example.demo.entity.SpendCategory;
import java.util.List;

public interface SpendCategoryService {

    SpendCategory create(SpendCategory category);

    SpendCategory getActiveById(Long id);

    List<SpendCategory> getAllActive();

    SpendCategory deactivateCategory(Long id);

    List<SpendCategory> getAllCategories(); // for test case 20
}
