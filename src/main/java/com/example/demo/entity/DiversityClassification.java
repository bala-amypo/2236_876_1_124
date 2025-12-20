package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // INVERSE side → NEVER deserialize from JSON
    @ManyToMany(mappedBy = "diversityClassifications")
    @JsonIgnore
    private Set<Supplier> suppliers = new HashSet<>();

    // -------------------- JPA LIFECYCLE --------------------

    @PrePersist
    public void preSave() {
        if (active == null) active = true;
        if (code != null) code = code.toUpperCase();
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
        if (code != null) code = code.toUpperCase();
    }

    // -------------------- GETTERS & SETTERS --------------------

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) {
        this.code = (code == null) ? null : code.toUpperCase();
    }

    public Boolean getActive() { return active; }

    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public Set<Supplier> getSuppliers() { return suppliers; }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
