package com.example.demo.controller;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @Operation(summary = "Create purchase order")
    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseOrder po) {
        return ResponseEntity.ok(service.createPurchaseOrder(po));
    }

    @Operation(summary = "Get all POs by supplier ID")
    @GetMapping("/supplier/{id}")
    public ResponseEntity<List<PurchaseOrder>> getBySupplier(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPurchaseOrdersBySupplier(id));
    }
}
