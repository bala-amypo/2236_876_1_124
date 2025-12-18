package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @PostMapping
    public Supplier create(@RequestBody Supplier supplier) {
        return service.createSupplier(supplier);
    }

    @PutMapping("/{id}")
    public Supplier update(@PathVariable Long id, @RequestBody Supplier supplier) {
        return service.updateSupplier(id, supplier);
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Long id) {
        return service.getSupplierById(id);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return service.getAllSuppliers();
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateSupplier(id);
    }
}
