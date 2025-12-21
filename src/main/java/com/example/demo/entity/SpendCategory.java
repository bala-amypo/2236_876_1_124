package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spend_categories")
public class SpendCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean active = true;

    // One-to-many relationship with PurchaseOrder
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();

    // --------------------
    // Constructors
    // --------------------
    public SpendCategory() {}

    public SpendCategory(String name) {
        this.name = name;
    }

    public SpendCategory(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

    // --------------------
    // Getters and Setters
    // --------------------
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Boolean getActive() { return active; }

    public void setActive(Boolean active) { this.active = active; }

    public Set<PurchaseOrder> getPurchaseOrders() { return purchaseOrders; }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    // --------------------
    // Lifecycle Hook
    // --------------------
    @PrePersist
    @PreUpdate
    public void preSave() {
        if (this.active == null) {
            this.active = true;
        }
    }
}
