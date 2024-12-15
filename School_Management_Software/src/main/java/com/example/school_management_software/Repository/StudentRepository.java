package com.example.school_management_software.Repository;

import com.example.school_management_software.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentById(Integer id);

    @Query("select s from Student s JOIN s.courses c where c.id=?1")
    List<Student> findStudentByCourseId(Integer courseId);
}
