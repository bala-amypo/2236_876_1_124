package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository repository;

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        if (po.getAmount().doubleValue() <= 0) {
            throw new BadRequestException("Amount must be greater than zero");
        }
        return repository.save(po);
    }

    @Override
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder po) {
        PurchaseOrder existing = getPurchaseOrderById(id);
        po.setId(existing.getId());
        return repository.save(po);
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found"));
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return repository.findAll();
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return repository.findBySupplierId(supplierId);
    }
}
