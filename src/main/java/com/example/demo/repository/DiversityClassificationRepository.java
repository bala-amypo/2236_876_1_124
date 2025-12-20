package com.example.demo.repository;

import com.example.demo.entity.DiversityClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiversityClassificationRepository extends JpaRepository<DiversityClassification, Long> {
    Optional<DiversityClassification> findByName(String name);
    boolean existsByName(String name);
}
