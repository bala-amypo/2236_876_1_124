// package com.example.OneToMany.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// import com.example.OneToMany.entity.Student;
// import com.example.OneToMany.service.StudentService;

// @RestController
// @RequestMapping("/students")
// public class StudentController {

//     @Autowired
//     private StudentService studentService;

//     @PostMapping
//     @PreAuthorize("hasRole('ADMIN')")
//     public Student saveStudent(@RequestBody Student student) {
//         return studentService.saveStudent(student);
//     }

//     @GetMapping("/{id}")
//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     public Student getStudentById(@PathVariable Long id) {
//         return studentService.getStudentById(id);
//     }

//     @DeleteMapping("/{id}")
//     @PreAuthorize("hasRole('ADMIN')")
//     public String delete(@PathVariable Long id) {
//         return studentService.deleteId(id);
//     }
// }
