package com.example.school_management_software.Service;

import com.example.school_management_software.ApiResponse.ApiException;
import com.example.school_management_software.InputDTO.InputStudentDTO;
import com.example.school_management_software.Model.Course;
import com.example.school_management_software.Model.Student;
import com.example.school_management_software.OutDTO.StudentDTO;
import com.example.school_management_software.Repository.CourseRepository;
import com.example.school_management_software.Repository.StudentRepository;
import com.example.school_management_software.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getName(),student.getMajor(),student.getGPA());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    public void addStudent(InputStudentDTO inputStudentDTO) {
        Student student = new Student(null, inputStudentDTO.getName(),inputStudentDTO.getAge(),inputStudentDTO.getMajor(),null,null);
        studentRepository.save(student);
    }

    public void studentRollInCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);
        if (student == null && course == null) {
            throw new ApiException("can't roll");
        }
        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        courseRepository.save(course);
    }

    public void updateStudent(Integer id, InputStudentDTO inputStudentDTO) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("student not found");
        }
        student.setName(inputStudentDTO.getName());
        student.setMajor(inputStudentDTO.getMajor());
        student.setAge(inputStudentDTO.getAge());
        studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null) {
            throw new ApiException("student not found");
        }
        studentRepository.delete(student);
    }

    public void changeStudentMajor(Integer studentId, String major) {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new ApiException("student not found");
        }
        student.setMajor(major);
        student.setCourses(null);
        studentRepository.save(student);

    }

    public List<StudentDTO> getStudentsByCourseId(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new ApiException("Course Not Found");
        }
        List<Student> students = studentRepository.findStudentByCourseId(course_id);
        if (students == null) {
            throw new ApiException("Student Not Found");
        }
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getName(),student.getMajor(),student.getGPA());
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
}
