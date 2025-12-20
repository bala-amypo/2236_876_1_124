package com.example.demo.repository;

import com.example.demo.entity.PurchaseOrder;
import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository {

    PurchaseOrder save(PurchaseOrder purchaseOrder);

    Optional<PurchaseOrder> findById(Long id);

    List<PurchaseOrder> findAll();

    List<PurchaseOrder> findBySupplier_Id(Long supplierId);
}
