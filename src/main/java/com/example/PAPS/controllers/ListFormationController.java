package com.example.PAPS.controllers;

import com.example.PAPS.dtos.*;
import com.example.PAPS.entities.*;
import com.example.PAPS.services.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/list")
public class ListFormationController {

    private final EmployeeServiceImpl employeeService;
    private final SpareService spareService;
    private final SupplierService supplierService;
    private final CarService carService;
    private final OrderService orderService;

    public ListFormationController(EmployeeServiceImpl employeeService, SpareService spareService, SupplierService supplierService, CarService carService, OrderService orderService) {
        this.employeeService = employeeService;
        this.spareService = spareService;
        this.supplierService = supplierService;
        this.carService = carService;
        this.orderService = orderService;
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SERVICE_MANAGER", "ROLE_PARTS_SELLING_MANAGER"})
    @GetMapping("/spares")
    public List<Spare> getSparesList() { //справочник запчастей
        return spareService.getAll();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_HR"})
    @GetMapping("/staff")
    public List<Employee> getStaffList() { //справочник сотрудников
        return employeeService.getEmployeeList();
    }

    @PreAuthorize("hasAnyRole('DIRECTOR', 'SUPPLIER_D')")
    @GetMapping("/suppliers")
    public List<Supplier> getSuppliersList() { //справочник поставщиков овощей
        return supplierService.getAllSuppliers();
    }

    @PreAuthorize("hasAnyRole('DIRECTOR', 'AUTO_SELLING_MANAGER')")
    @GetMapping("/cars")
    public List<Car> getCars() { // справочник машин (таких как я прям, гы)
        return carService.getAllCars();
    }

    @PreAuthorize("hasAnyRole('SERVICE_MANAGER', 'MAINTENANCE_WORKER')")
    @GetMapping("/maintenance-orders")
    public List<MaintenanceOrder> getMaintenanceOrders() {
        return orderService.getAllOrders();
    }

    @Secured("ROLE_DIRECTOR")
    @GetMapping("/add/spare")
    public void addSpareToList(@RequestBody SpareDto spareDto) {
        spareService.add(spareDto);
    }

    @PreAuthorize("hasRole('HR')")
    @PostMapping("/add/employee")
    public void addEmployeeToList(@RequestBody EmployeeDto employeeDto) {
        employeeService.add(employeeDto);
    }

    @Secured("ROLE_SUPPLIER_D")
    @PostMapping("/add/supplier")
    public void addSupplierToList(@RequestBody SupplierDto supplierDto) {
        supplierService.add(supplierDto);
    }

    @PreAuthorize("hasRole('SUPPLIER_D')")
    @PostMapping("/add/car")
    public void addCarToList(@RequestBody CarDto carDto) {
        carService.add(carDto);
    }

    @PreAuthorize("hasRole('SERVICE_MANAGER')")
    @PostMapping("/add/maintenance-order")
    public void addMaintenanceOrderToList(@RequestBody MaintenanceOrderDto maintenanceOrderDto) {
        orderService.add(maintenanceOrderDto);
    }
}
