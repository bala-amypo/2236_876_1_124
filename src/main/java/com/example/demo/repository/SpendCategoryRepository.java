package com.example.demo.repository;

import com.example.demo.entity.SpendCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpendCategoryRepository extends JpaRepository<SpendCategory, Long> {

    // Custom query for active categories
    List<SpendCategory> findByActiveTrue();
}
