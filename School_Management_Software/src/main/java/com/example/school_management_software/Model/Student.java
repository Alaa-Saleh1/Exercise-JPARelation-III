package com.example.school_management_software.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    @Column(nullable = false)
    private String name;

    @NotNull( message = "age must not be null")
    @Column(nullable = false)
    private Integer age;

    @NotEmpty(message = "major must not be empty")
    @Column(nullable = false)
    private String major;

    @Column(columnDefinition = "DOUBLE")
    private Double GPA;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

}
