package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository poRepository;
    private final SupplierRepository supplierRepository;
    private final SpendCategoryRepository categoryRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository poRepository,
                                    SupplierRepository supplierRepository,
                                    SpendCategoryRepository categoryRepository) {
        this.poRepository = poRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return poRepository.findAll();
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return poRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found with id " + id));
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        // Validation occurs in @PrePersist of entity
        return poRepository.save(po);
    }

    @Override
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder updatedPo) {
        PurchaseOrder po = poRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found with id " + id));

        po.setPoNumber(updatedPo.getPoNumber());
        po.setAmount(updatedPo.getAmount());
        po.setDateIssued(updatedPo.getDateIssued());
        po.setSupplier(updatedPo.getSupplier());
        po.setCategory(updatedPo.getCategory());
        po.setNotes(updatedPo.getNotes());

        return poRepository.save(po);
    }

    @Override
    public void deletePurchaseOrder(Long id) {
        PurchaseOrder po = poRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found with id " + id));
        poRepository.delete(po);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return poRepository.findBySupplier_Id(supplierId);
    }
}
