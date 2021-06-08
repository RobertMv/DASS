package com.example.PAPS.repositories;

import com.example.PAPS.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    Employee findByName(String name);

    List<Employee> findAllByName(String name);

    Employee findByEmail(String email);
}
