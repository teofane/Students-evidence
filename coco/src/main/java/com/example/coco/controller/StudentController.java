package com.example.coco.controller;

import com.example.coco.entity.Student;
import com.example.coco.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    private final StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/studenti-salvati")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        logger.info("Inside saveStudent of StudentController");
        return studentService.saveStudent(student);
    }

    @GetMapping("/studenti-salvati")
    public ResponseEntity<List<Student>> fetchStudentsList() {
        logger.info("Inside fetchStudentsList of StudentController");
        return studentService.getStudents();
    }

    @GetMapping("/studenti-salvati/{id}")
    public ResponseEntity<Student> fetchStudentById(@PathVariable("id") Long studentId) {
        logger.info("Inside fetchStudentById of StudentController");
        return studentService.fetchStudentById(studentId);
    }

    @DeleteMapping("/studenti-salvati/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable("id") Long studentId) {
        logger.info("Inside deleteStudentById of StudentController");
        return studentService.deleteStudentById(studentId);
    }

    @PutMapping("/studenti-salvati/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long studentId, @RequestBody Student student) {
        logger.info("Inside updateStudent of StudentController");
        return studentService.updateStudent(studentId, student);
    }

    @GetMapping("/studenti-salvati/nume/{name}")
    public ResponseEntity<Student> fetchStudentByName(@PathVariable("name") String studentName) {
        logger.info("Inside fetchStudentByName of StudentController");
        return studentService.fetchStudentByName(studentName);
    }

    public StudentController(com.example.coco.service.StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/lista-studenti")
    public ResponseEntity<List<Student>> getStudents() {
        logger.info("Inside getStudents of StudentController");
        return studentService.getStudents();
    }
}
