package com.example.demo.service;

import com.example.demo.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {

    List<PurchaseOrder> getAllPurchaseOrders();

    PurchaseOrder getPurchaseOrderById(Long id);

    PurchaseOrder createPurchaseOrder(PurchaseOrder po);

    PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder po);

    void deletePurchaseOrder(Long id);

    List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId);
}
