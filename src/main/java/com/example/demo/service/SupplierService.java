package com.example.demo.service;

import com.example.demo.entity.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);
    Supplier updateSupplier(Long id, Supplier supplier);
    void deleteSupplier(Long id);
    Supplier getSupplierById(Long id);
    List<Supplier> getAllSuppliers();
}
