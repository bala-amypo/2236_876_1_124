package com.example.demo.controller;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @PostMapping
    public PurchaseOrder create(@RequestBody PurchaseOrder purchaseOrder) {
        return service.createPurchaseOrder(purchaseOrder);
    }

    @GetMapping
    public List<PurchaseOrder> getAll() {
        return service.getAllPurchaseOrders();
    }

    @GetMapping("/supplier/{supplierId}")
    public List<PurchaseOrder> getBySupplier(@PathVariable Long supplierId) {
        return service.getPurchaseOrdersBySupplier(supplierId);
    }
}
