package com.example.school_management_software.OutDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentDTO {

    private String name;

    private String major;

    private Double GPA;
}
