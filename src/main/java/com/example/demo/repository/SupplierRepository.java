package com.example.demo.repository;

import com.example.demo.entity.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    Supplier save(Supplier supplier);
    Optional<Supplier> findById(Long id);
    List<Supplier> findAll();
    boolean existsByEmail(String email);
    List<Supplier> findByIsActiveTrue();
}
