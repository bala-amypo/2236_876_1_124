package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;
import java.util.List;

public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    public Supplier create(Supplier s) {
        return service.createSupplier(s);
    }

    public List<Supplier> getAll() {
        return service.getAllSuppliers();
    }
}
