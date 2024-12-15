package com.example.school_management_software.OutDTO;

import com.example.school_management_software.Model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TeacherDTO {

    private String name;

    private Integer age;

    private String email;

    private Double salary;

    private OutAddressDTO address;
}
