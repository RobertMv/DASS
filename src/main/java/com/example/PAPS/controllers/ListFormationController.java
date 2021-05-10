package com.example.PAPS.controllers;

import com.example.PAPS.dtos.*;
import com.example.PAPS.entities.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/list")
public class ListFormationController {

    @PreAuthorize("hasAnyRole('DIRECTOR', 'SERVICE_MANAGER', 'PARTS_SELLING_MANAGER')")
    @GetMapping("/spares")
    public List<Spare> getSparesList() { //справочник запчастей
        List<Spare> spares = new ArrayList<>();
        return spares;
    }

    @PreAuthorize("hasAnyRole('DIRECTOR','HR')")
    @GetMapping("/staff")
    public List<Employee> getStaffList() { //справочник сотрудников
        List<Employee> staff = new ArrayList<>();
        return staff;
    }

    @PreAuthorize("hasAnyRole('DIRECTOR', 'SUPPLIER_D')")
    @GetMapping("/suppliers")
    public List<Supplier> getSuppliersList() { //справочник поставщиков овощей
        List<Supplier> suppliers = new ArrayList<>();
        return suppliers;
    }

    @PreAuthorize("hasAnyRole('DIRECTOR', 'AUTO_SELLING_MANAGER')")
    @GetMapping("/cars")
    public List<Car> getCars() { // справочник машин (таких как я прям, гы)
        List<Car> cars = new ArrayList<>();
        return cars;
    }

    @PreAuthorize("hasAnyRole('SERVICE_MANAGER', 'MAINTENANCE_WORKER')")
    @GetMapping("/maintenance-orders")
    public List<MaintenanceOrder> getMaintenanceOrders() {
        List<MaintenanceOrder> maintenanceOrders = new ArrayList<>();
        return maintenanceOrders;
    }

    @PreAuthorize("hasRole('SUPPLIER_D')")
    @PostMapping("/add/Spare")
    public void addSpareToList(@RequestBody SpareDto spareDto) {
        //spareService.add(spareDto);
    }

    @PreAuthorize("hasRole('HR')")
    @PostMapping("/add/employee")
    public void addEmployeeToList(@RequestBody EmployeeDto employeeDto) {
        //employeeService.add(employeeDto);
    }

    @PreAuthorize("hasRole('SUPPLIER_D')")
    @PostMapping("/add/supplier")
    public void addSupplierToList(@RequestBody SupplierDto supplierDto) {
        //supplierService.add(supplierDto);
    }

    @PreAuthorize("hasRole('SUPPLIER_D')")
    @PostMapping("/add/car")
    public void addCarToList(@RequestBody CarDto carDto) {
        //carService.add(carDto);
    }

    @PreAuthorize("hasRole('SERVICE_MANAGER')")
    @PostMapping("/add/maintenance-order")
    public void addMaintenanceOrderToList(@RequestBody MaintenanceOrderDto maintenanceOrderDto) {
        //maintenanceOrderService.add(maintenanceOrderDto);
    }
}
