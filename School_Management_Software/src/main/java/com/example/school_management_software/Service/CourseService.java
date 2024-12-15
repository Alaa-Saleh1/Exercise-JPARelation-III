package com.example.school_management_software.Service;

import com.example.school_management_software.ApiResponse.ApiException;
import com.example.school_management_software.Model.Course;
import com.example.school_management_software.Model.Student;
import com.example.school_management_software.Model.Teacher;
import com.example.school_management_software.OutDTO.CourseDTO;
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
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    public List<CourseDTO> getAllCourse(){
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getName());
            courseDTOs.add(courseDTO);
        }
        return courseDTOs;
    }

    public void addCourse(Integer teacher_id,Course course){
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if (teacher == null) {
            throw new ApiException("Teacher Not Found");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void updateCourse(Integer id, Course course){
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse == null) {
            throw new ApiException("Course Not Found");
        }
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }
    public void deleteCourse(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new ApiException("Course Not Found");
        }
        courseRepository.delete(course);
    }

    public String getCourseTeacherName(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);
        Teacher teacher = teacherRepository.findTeacherById(course.getTeacher().getId());
        if (teacher == null) {
            throw new ApiException("Teacher Not Found");
        }
        return teacher.getName();
    }


}
