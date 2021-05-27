package com.example.PAPS.services;

import com.example.PAPS.entities.Employee;
import com.example.PAPS.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }

}
