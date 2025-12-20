package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
        name = "supplier_diversity",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "diversity_id")
    )
    private Set<DiversityClassification> diversityClassifications = new HashSet<>();

    public Supplier() {}

    public Supplier(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<DiversityClassification> getDiversityClassifications() { return diversityClassifications; }
    public void setDiversityClassifications(Set<DiversityClassification> diversityClassifications) {
        this.diversityClassifications = diversityClassifications;
    }
}
