package com.example.demo.repository;

import com.example.demo.entity.SpendCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendCategoryRepository extends JpaRepository<SpendCategory, Long> {
    // Custom queries if needed
}
