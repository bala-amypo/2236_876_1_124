package com.example.demo.controller;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@Tag(name = "Purchase Order API")
public class PurchaseOrderController {

    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseOrder po) {
        return ResponseEntity.ok(service.createPurchaseOrder(po));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> update(
            @PathVariable Long id,
            @RequestBody PurchaseOrder po) {
        return ResponseEntity.ok(service.updatePurchaseOrder(id, po));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPurchaseOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAll() {
        return ResponseEntity.ok(service.getAllPurchaseOrders());
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrder>> getBySupplier(
            @PathVariable Long supplierId) {
        return ResponseEntity.ok(service.getPurchaseOrdersBySupplier(supplierId));
    }
}