package com.example.coco.service;

import com.example.coco.entity.Student;
import com.example.coco.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<Student> saveStudent(Student student) {

        HttpHeaders httpHeaders = new HttpHeaders();
        if (student.getName().isEmpty()) {
            httpHeaders.add("Responded", "No Content For Name");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(httpHeaders)
                    .body(null);
        }
        if (student.getAge() == null) {
            httpHeaders.add("Responded", "No Content For Age");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(httpHeaders)
                    .body(null);
        }
        if (student.getDob().isEmpty()) {
            httpHeaders.add("Responded", "No Content For DoB");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(httpHeaders)
                    .body(null);
        }
        if (student.getEmail().isEmpty()) {
            httpHeaders.add("Responded", "No Content For Email");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(httpHeaders)
                    .body(null);
        }

        studentRepository.save(student);
        httpHeaders.add("Responded", "Student saved");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(student);
    }


    public ResponseEntity<List<Student>> getStudents() {

        List<Student> studentList = studentRepository.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        if (studentList.isEmpty()) {
            httpHeaders.add("Responded", "No students saved");
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(null); // status is still ok
        }
        httpHeaders.add("Responded", "Students returned");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(studentList);
    }

    public List<Student> fetchStudentsList() {

        return studentRepository.findAll();
    }

    @Override
    public ResponseEntity<Student> fetchStudentById(Long studentId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        Optional<Student> s = studentRepository.findById(studentId);
        if (s.isEmpty()) {
            httpHeaders.add("Responded", "No user found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null);
        } else {
            httpHeaders.add("Responded", "User has been found");
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(s.get());
        }
    }

    @Override
    public ResponseEntity<Student> deleteStudentById(Long studentId) {

        Optional<Student> studDB = studentRepository.findById(studentId);
        HttpHeaders httpHeaders = new HttpHeaders();
        if (studDB.isEmpty()) {
            httpHeaders.add("Responded", "Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders)
                    .body(null);
        } else {
            studentRepository.deleteById(studentId);
            httpHeaders.add("Responded", "User deleted");
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders)
                    .body(studDB.get());
        }
    }

    @Override
    public ResponseEntity<Student> updateStudent(Long studentId, Student student) {

        Optional<Student> studDB = studentRepository.findById(studentId);
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        if (studDB.isEmpty()) {
            httpHeaders.add("Responded", "Student not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders)
                    .body(null);
        } else {

            if (Objects.nonNull(student.getName()) &&
                    !"".equalsIgnoreCase(student.getName())) {
                studDB.get().setName(student.getName());
            }

            if (Objects.nonNull(student.getEmail()) &&
                    !"".equalsIgnoreCase(student.getEmail())) {
                studDB.get().setEmail(student.getEmail());
            }

            if (Objects.nonNull(student.getDob()) &&
                    !"".equalsIgnoreCase(student.getDob())) {
                studDB.get().setDob(student.getDob());
            }

            if (Objects.nonNull(student.getAge()) &&
                    student.getAge() != null) {
                studDB.get().setAge(student.getAge());
            }

            studentRepository.save(studDB.get());
            httpHeaders.add("Responded", "User updated");
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(studDB.get());

        }

    }

    @Override
    public ResponseEntity<Student> fetchStudentByName(String studentName) {

        HttpHeaders httpHeaders = new HttpHeaders();
        Student student = studentRepository.findByNameIgnoreCase(studentName);
        if (student == null) {
            httpHeaders.add("Responded", "No user found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(httpHeaders).body(null);
        } else {

            httpHeaders.add("Responded", "User has been found");
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(student);
        }
    }

}
