package com.example.studentmanagement.service;
import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testGetStudentById() {

        Student student = new Student();
        student.setName("Vrushali");

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);

        assertEquals("Vrushali", result.getName());

        verify(studentRepository, times(1))
                .findById(1L);
    }
    @Test
    void testSaveStudent() {

        Student student = new Student();
        student.setName("Vrushali");
        student.setEmail("vrushali@example.com");
        student.setCourse("E&TC");

        when(studentRepository.save(student))
                .thenReturn(student);

        Student savedStudent =
                studentService.saveStudent(student);

        assertEquals("Vrushali",
                savedStudent.getName());

        verify(studentRepository, times(1))
                .save(student);
    }
    @Test
    void testGetAllStudents() {

        Student student1 = new Student();
        student1.setName("Vrushali");

        Student student2 = new Student();
        student2.setName("Rahul");

        when(studentRepository.findAll())
                .thenReturn(List.of(student1, student2));

        List<Student> students =
                studentService.getAllStudents();

        assertEquals(2, students.size());

        verify(studentRepository, times(1))
                .findAll();
    }
}