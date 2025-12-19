package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diversity-classifications")
public class DiversityClassificationController {

    private final DiversityClassificationService service;

    public DiversityClassificationController(
            DiversityClassificationService service) {
        this.service = service;
    }

    @PostMapping
    public DiversityClassification create(
            @RequestBody DiversityClassification classification) {
        return service.create(classification);
    }

    @GetMapping("/{id}")
    public DiversityClassification get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<DiversityClassification> listActive() {
        return service.getAllActive();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }
}
