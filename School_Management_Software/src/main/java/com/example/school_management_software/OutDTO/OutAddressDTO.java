package com.example.school_management_software.OutDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OutAddressDTO {

    private Integer teacher_id;

    private String area;

    private String street;

    private Integer buildingNumber;
}
