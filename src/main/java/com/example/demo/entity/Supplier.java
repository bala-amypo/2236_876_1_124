package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // OWNING side – manage via service, not JSON
    @ManyToMany
    @JoinTable(
        name = "supplier_classifications",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "classification_id")
    )
    @JsonIgnore
    private Set<DiversityClassification> diversityClassifications = new HashSet<>();

    // -------------------- JPA LIFECYCLE --------------------

    @PrePersist
    public void prePersist() {
        if (isActive == null) isActive = true;
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // -------------------- GETTERS & SETTERS --------------------

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getRegistrationNumber() { return registrationNumber; }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Boolean getIsActive() { return isActive; }

    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<DiversityClassification> getDiversityClassifications() {
        return diversityClassifications;
    }

    public void setDiversityClassifications(Set<DiversityClassification> diversityClassifications) {
        this.diversityClassifications = diversityClassifications;
    }
}
