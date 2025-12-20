package com.example.demo.service.impl;

import com.example.demo.entity.Department;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department createDepartment(Department department) {
        if (repository.existsByName(department.getName())) {
            throw new BadRequestException("Department name already exists");
        }
        return repository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Department not found"));
    }

    @Override
    public void deactivateDepartment(Long id) {
        Department dept = getDepartmentById(id);
        dept.setActive(false);
        repository.save(dept);
    }
}
