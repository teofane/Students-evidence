package com.example.coco.repository;

import com.example.coco.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public Student findByName(String name);

    public Student findByNameIgnoreCase(String name);

    public List<Student> findAll();// sorteaza studentii


}
