package com.example.coco.service;

import com.example.coco.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentServiceInterface {

    public ResponseEntity<Student> saveStudent(Student student);

    public ResponseEntity<List<Student>> getStudents();

    public ResponseEntity<Student> fetchStudentById(Long studentId);

    public ResponseEntity<Student> deleteStudentById(Long studentId);

    public ResponseEntity<Student> updateStudent(Long studentId, Student student);

    public ResponseEntity<Student> fetchStudentByName(String studentName);

}
