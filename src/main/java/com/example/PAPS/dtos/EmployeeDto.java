package com.example.PAPS.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id, passportID, INN, SNILS;
    private String name, surname, patronymic, sex, phone, email, departmentCode, positionCode;
    private LocalDate dateOfBirth;
    private LocalDate dateOfEmployment;
    private LocalDate dateOfDismissal;
}
