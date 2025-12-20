package com.example.demo.repository;

import com.example.demo.entity.SpendCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SpendCategoryRepository extends JpaRepository<SpendCategory, Long> {
    boolean existsByName(String name);
    boolean existsByCode(String code);
    Optional<SpendCategory> findByIdAndActiveTrue(Long id);
    List<SpendCategory> findAllByActiveTrue();
}
