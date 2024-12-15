package com.example.school_management_software.Controller;

import com.example.school_management_software.ApiResponse.ApiResponse;
import com.example.school_management_software.InputDTO.InputStudentDTO;
import com.example.school_management_software.Model.Student;
import com.example.school_management_software.OutDTO.StudentDTO;
import com.example.school_management_software.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid InputStudentDTO student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("student added successfully"));
    }

    @PutMapping("/assign-course/{student_id}/{course_id}")
    public ResponseEntity<?> assignCourse(@PathVariable int student_id, @PathVariable int course_id) {
        studentService.studentRollInCourse(student_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("student assigned successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody @Valid InputStudentDTO student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("student deleted successfully"));

    }

    @GetMapping("/get-student/{course_id}")
    public ResponseEntity<?> getStudentsByCourseId(@PathVariable Integer course_id) {
        List<StudentDTO> studentDTOs= studentService.getStudentsByCourseId(course_id);
        return ResponseEntity.status(200).body(studentDTOs);
    }

    @PutMapping("/change-major/{student_id}/{major}")
    public ResponseEntity<?> changeMajor(@PathVariable Integer student_id, @PathVariable String major) {
        studentService.changeStudentMajor(student_id, major);
        return ResponseEntity.status(200).body(new ApiResponse("student major updated successfully"));
    }
}
