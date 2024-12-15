package com.example.school_management_software.InputDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class InputStudentDTO {

    private String name;
    private Integer age;
    private String major;
}
