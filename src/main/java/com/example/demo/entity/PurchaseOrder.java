package com.example.demo.entity;

import com.example.demo.exception.BadRequestException;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String poNumber;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate dateIssued;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private SpendCategory category;

    @Column(length = 500)
    private String notes;

    // --------------------
    // Constructors
    // --------------------
    public PurchaseOrder() {}

    public PurchaseOrder(String poNumber, BigDecimal amount, LocalDate dateIssued, Supplier supplier, SpendCategory category) {
        this.poNumber = poNumber;
        this.amount = amount;
        this.dateIssued = dateIssued;
        this.supplier = supplier;
        this.category = category;
    }

    // --------------------
    // Getters & Setters
    // --------------------
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getPoNumber() { return poNumber; }

    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getDateIssued() { return dateIssued; }

    public void setDateIssued(LocalDate dateIssued) { this.dateIssued = dateIssued; }

    public Supplier getSupplier() { return supplier; }

    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public SpendCategory getCategory() { return category; }

    public void setCategory(SpendCategory category) { this.category = category; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    // --------------------
    // Business Rules
    // --------------------
    @PrePersist
    @PreUpdate
    public void validate() {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Purchase order amount must be positive");
        }

        if (dateIssued == null) {
            throw new BadRequestException("Purchase order date cannot be null");
        }

        if (dateIssued.isAfter(LocalDate.now())) {
            throw new BadRequestException("Purchase order date cannot be in the future");
        }

        if (supplier == null || !Boolean.TRUE.equals(supplier.getIsActive())) {
            throw new BadRequestException("Cannot assign PO to inactive supplier");
        }

        if (category == null || !Boolean.TRUE.equals(category.getActive())) {
            throw new BadRequestException("Cannot assign PO to inactive category");
        }
    }
}
