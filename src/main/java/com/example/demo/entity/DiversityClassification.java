package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "diversity_classifications",
    uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class DiversityClassification {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;

    private Boolean active = true;

    public DiversityClassification() {}

    public DiversityClassification(String code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
