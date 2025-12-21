package com.example.demo.repository;

import com.example.demo.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    // Find all POs for a given supplier ID
    List<PurchaseOrder> findBySupplier_Id(Long supplierId);

    // Optional: find all active POs (not strictly required by current tests)
}
