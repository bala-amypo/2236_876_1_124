package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String poNumber;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "spend_category_id", nullable = false)
    private SpendCategory spendCategory;

    // ✅ Required no-arg constructor
    public PurchaseOrder() {
    }

    public PurchaseOrder(String poNumber, BigDecimal amount, LocalDate orderDate) {
        this.poNumber = poNumber;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    // ---------- getters & setters ----------

    public Long getId() {
        return id;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public SpendCategory getSpendCategory() {
        return spendCategory;
    }

    public void setSpendCategory(SpendCategory spendCategory) {
        this.spendCategory = spendCategory;
    }
}
