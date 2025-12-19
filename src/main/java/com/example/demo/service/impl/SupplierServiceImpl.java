package com.example.demo.service.impl;

import com.example.demo.entity.Supplier;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repo;

    public SupplierServiceImpl(SupplierRepository repo) {
        this.repo = repo;
    }

    public Supplier createSupplier(Supplier s) {
        return repo.save(s);
    }

    public Supplier getSupplierById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    public List<Supplier> getAllSuppliers() {
        return repo.findAll();
    }

    public void deactivateSupplier(Long id) {
        Supplier s = getSupplierById(id);
        s.setIsActive(false);
        repo.save(s);
    }
}
