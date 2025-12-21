package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/targets")
public class DiversityTargetController {

    private final DiversityTargetService service;

    public DiversityTargetController(DiversityTargetService service) {
        this.service = service;
    }

    @Operation(summary = "Create diversity target")
    @PostMapping
    public ResponseEntity<DiversityTarget> create(@RequestBody DiversityTarget target) {
        return ResponseEntity.ok(service.createTarget(target));
    }

    @Operation(summary = "Get targets by year")
    @GetMapping("/year/{year}")
    public ResponseEntity<List<DiversityTarget>> getByYear(@PathVariable int year) {
        return ResponseEntity.ok(service.getTargetsByYear(year));
    }

    @Operation(summary = "Deactivate target")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateTarget(id);
        return ResponseEntity.ok().build();
    }
}
