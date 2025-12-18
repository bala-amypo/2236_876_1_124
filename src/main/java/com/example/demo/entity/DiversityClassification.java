package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;

    private Boolean active;

    @ManyToMany(mappedBy = "diversityClassifications")
    private Set<Supplier> suppliers = new HashSet<>();

    @OneToMany(mappedBy = "classification")
    private Set<DiversityTarget> targets = new HashSet<>();

    // ✅ No-arg constructor
    public DiversityClassification() {
    }

    // ✅ Optional constructor
    public DiversityClassification(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        if (this.active == null) {
            this.active = true;
        }
        if (this.code != null) {
            this.code = this.code.toUpperCase();
        }
    }

    // ---------- getters and setters ----------

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }
}
