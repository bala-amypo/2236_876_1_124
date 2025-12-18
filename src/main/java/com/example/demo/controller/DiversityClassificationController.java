package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diversity-classifications")
public class DiversityClassificationController {

    @Autowired
    private DiversityClassificationService service;

    @PostMapping
    public DiversityClassification create(@RequestBody DiversityClassification c) {
        return service.createClassification(c);
    }

    @PutMapping("/{id}")
    public DiversityClassification update(@PathVariable Long id,
                                          @RequestBody DiversityClassification c) {
        return service.updateClassification(id, c);
    }

    @GetMapping
    public List<DiversityClassification> getAll() {
        return service.getAllClassifications();
    }

    @GetMapping("/{id}")
    public DiversityClassification getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateClassification(id);
    }
}
