package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // JPQL Query
    @Query("SELECT s FROM Student s WHERE s.course = ?1")
    List<Student> getStudentsByCourseJPQL(String course);

    // Native SQL Query
    @Query(value = "SELECT * FROM students WHERE course = ?1",
            nativeQuery = true)
    List<Student> getStudentsByCourseNative(String course);

    List<Student> findByName(String name);

    Student findByEmail(String email);

    List<Student> findByCourse(String course);

    List<Student> findByNameContaining(String name);

    @Transactional
    @Modifying
    void deleteByName(String name);
}