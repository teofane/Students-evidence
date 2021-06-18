package com.example.coco.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "studenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @NotBlank(message = "Please insert student name")
    @Length(max = 30, message = "The name is too long")
    private String name;
   @Pattern(regexp = "\\b(\\w+(?:@yahoo\\.com|@gmail\\.com))\\b", message = "The email have to be yahoo or gmail")
    private String email;
    private String dob;
    private Integer age;
}