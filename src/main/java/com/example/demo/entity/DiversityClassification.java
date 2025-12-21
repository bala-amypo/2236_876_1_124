package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class DiversityClassification {

    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private Boolean active;

    @PrePersist
    public void preSave() {
        if (active == null) active = true;
        if (code != null) code = code.toUpperCase();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code.toUpperCase(); }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
