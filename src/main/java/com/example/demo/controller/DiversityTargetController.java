package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/targets")
public class DiversityTargetController {

    private final DiversityTargetService targetService;

    public DiversityTargetController(DiversityTargetService targetService) {
        this.targetService = targetService;
    }

    @PostMapping
    public DiversityTarget createTarget(@RequestBody DiversityTarget target) {
        return targetService.createTarget(target);
    }

    @GetMapping("/{id}")
    public DiversityTarget getTarget(@PathVariable Long id) {
        return targetService.getTargetById(id);
    }

    @GetMapping
    public List<DiversityTarget> getAllTargets() {
        return targetService.getAllTargets();
    }

    @GetMapping("/year/{year}")
    public List<DiversityTarget> getTargetsByYear(@PathVariable int year) {
        return targetService.getTargetsByYear(year);
    }

    @PutMapping("/{id}/deactivate")
    public DiversityTarget deactivateTarget(@PathVariable Long id) {
        targetService.deactivateTarget(id);
        return targetService.getTargetById(id);
    }
}
