package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spend_categories")
public class SpendCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean active;

    @PrePersist
    public void preSave() {
        if (active == null) active = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
