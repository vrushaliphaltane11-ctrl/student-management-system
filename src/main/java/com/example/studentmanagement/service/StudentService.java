package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /// Save Student
    public StudentResponse saveStudent(StudentRequest request) {

        Student student = new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());

        Student savedStudent = studentRepository.save(student);

        StudentResponse response = new StudentResponse();

        response.setId(savedStudent.getId());
        response.setName(savedStudent.getName());
        response.setEmail(savedStudent.getEmail());
        response.setCourse(savedStudent.getCourse());

        return response;
    }

    // Get All Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public List<Student> getStudentsByCourseJPQL(String course) {
        return studentRepository.getStudentsByCourseJPQL(course);
    }
    public List<Student> getStudentsByCourseNative(String course) {
        return studentRepository.getStudentsByCourseNative(course);
    }

    // Pagination
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // Get Student By ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id " + id));
    }

    // Find By Name
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    // Find By Email
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // Find By Course
    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    // Find By Name Containing
    public List<Student> getStudentsByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

    // Delete By ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Delete By Name
    public void deleteStudentByName(String name) {
        studentRepository.deleteByName(name);
    }

    // Update Student
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id " + id));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourse(student.getCourse());

        return studentRepository.save(existingStudent);
    }
}