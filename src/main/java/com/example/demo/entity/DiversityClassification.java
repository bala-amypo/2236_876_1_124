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

    private String code;
    private String description;
    private Boolean active;

    @ManyToMany(mappedBy = "diversityClassifications")
    private Set<Supplier> suppliers = new HashSet<>();

    @PrePersist
    @PreUpdate
    public void preSave() {
        if (active == null) active = true;
        if (code != null) code = code.toUpperCase();
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
