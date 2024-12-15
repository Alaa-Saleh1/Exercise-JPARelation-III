package com.example.school_management_software.Service;

import com.example.school_management_software.ApiResponse.ApiException;
import com.example.school_management_software.Model.Address;
import com.example.school_management_software.Model.Teacher;
import com.example.school_management_software.OutDTO.OutAddressDTO;
import com.example.school_management_software.OutDTO.TeacherDTO;
import com.example.school_management_software.Repository.AddressRepository;
import com.example.school_management_software.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;


    public List<TeacherDTO> getAllTeachers() {

        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOs= new ArrayList<>();
        for (Teacher teacher : teachers) {
            Address address = teacher.getAddress();
            OutAddressDTO outAddressDTO = new OutAddressDTO(address.getId(),address.getArea(),address.getStreet(),address.getBuildingNumber());
            TeacherDTO teacherDTO=new TeacherDTO(teacher.getName(),teacher.getAge(),teacher.getEmail(),teacher.getSalary(),outAddressDTO);
            teacherDTOs.add(teacherDTO);
        }
        return teacherDTOs;
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, Teacher teacher) {
        Teacher oldTeacher = teacherRepository.findTeacherById(id);
        if (oldTeacher == null) {
            throw new ApiException("Teacher not found");
        }
        oldTeacher.setAddress(teacher.getAddress());
        oldTeacher.setName(teacher.getName());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setSalary(teacher.getSalary());
        teacherRepository.save(oldTeacher);

    }

    public void deleteTeacher(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        Address address = addressRepository.findAddressById(id);
        if (address == null) {
            throw new ApiException("Address not found");
        }
        teacher.setAddress(null);
        teacherRepository.save(teacher);
        addressRepository.delete(address);
    }

    public Teacher getTeacher(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }
        return teacher;
    }

}
