package com.example.demo.service;

import com.example.demo.entity.PurchaseOrder;
import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {
    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> getAllPurchaseOrders();
    Optional<PurchaseOrder> getPurchaseOrderById(Long id);
    PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder);
    void deletePurchaseOrder(Long id);
}
