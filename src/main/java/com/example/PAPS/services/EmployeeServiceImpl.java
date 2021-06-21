package com.example.PAPS.services;

import com.example.PAPS.dtos.EmployeeDto;
import com.example.PAPS.entities.Employee;
import com.example.PAPS.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;
    private final DtoEntityConversionService conversionService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DtoEntityConversionService conversionService) {
        this.employeeRepository = employeeRepository;
        this.conversionService = conversionService;
    }

    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }

    public void add(EmployeeDto employeeDto) {
        employeeRepository.save(conversionService.convert(employeeDto));
    }

    public void delete(EmployeeDto employeeDto){
        employeeRepository.delete(conversionService.convert(employeeDto));
    }
}
