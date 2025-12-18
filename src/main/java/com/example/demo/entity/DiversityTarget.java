package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer targetYear;

    @Column(nullable = false)
    private Double targetPercentage;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "classification_id", nullable = false)
    private DiversityClassification classification;

    // ✅ Required no-arg constructor
    public DiversityTarget() {
    }

    public DiversityTarget(Integer targetYear, Double targetPercentage) {
        this.targetYear = targetYear;
        this.targetPercentage = targetPercentage;
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        if (this.active == null) {
            this.active = true;
        }
    }

    // --
