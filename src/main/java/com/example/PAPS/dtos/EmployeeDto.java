package com.example.PAPS.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private  Long id, passportID, INN, SNILS;
    private String name, surname, patronymic, sex, phone, email, departmentCode, positionCode;
    private Date dateOfBirth, dateOfEmployment, dateOfDismissal;
}
