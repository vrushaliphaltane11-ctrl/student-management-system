package com.example.studentmanagement.controller;
import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import io.swagger.v3.oas.annotations.Operation;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Add Student

    @PostMapping
    public
     StudentResponse saveStudent(
            @Valid @RequestBody StudentRequest request) {

        return studentService.saveStudent(request);
    }
    // Get All Students
    @GetMapping
    public Page<Student> getStudents(Pageable pageable) {
        return studentService.findAll(pageable);
    }
    @GetMapping("/search/{name}")
    public List<Student> searchStudents(@PathVariable String name) {
        return studentService.getStudentsByNameContaining(name);
    }
    // Get Student By Id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    @GetMapping("/jpql/course/{course}")
    public List<Student> getStudentsByCourseJPQL(
            @PathVariable String course) {
        return studentService.getStudentsByCourseJPQL(course);
    }
    @GetMapping("/native/course/{course}")
    public List<Student> getStudentsByCourseNative(
            @PathVariable String course) {
        return studentService.getStudentsByCourseNative(course);
    }
    // Delete Student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }
    @DeleteMapping("/name/{name}")
    public String deleteStudentByName(@PathVariable String name) {
        studentService.deleteStudentByName(name);
        return "Student deleted successfully";
    }

    // Update Student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
    @Operation(summary = "Get all students")


    @GetMapping("/name/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return studentService.getStudentsByName(name);
    }
    @GetMapping("/email/{email}")
    public Student getStudentByEmail(@PathVariable String email) {
        return studentService.getStudentByEmail(email);
    }
    @GetMapping("/course/{course}")
    public List<Student> getStudentsByCourse(@PathVariable String course) {
        return studentService.getStudentsByCourse(course);
    }

}