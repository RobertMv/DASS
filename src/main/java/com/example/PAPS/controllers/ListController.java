package com.example.PAPS.controllers;

import com.example.PAPS.dtos.*;
import com.example.PAPS.entities.*;
import com.example.PAPS.services.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ListController {

    private final EmployeeServiceImpl employeeService;
    private final SpareService spareService;
    private final SupplierService supplierService;
    private final CarService carService;
    private final OrderService orderService;
    private final ClientService clientService;

    public ListController(EmployeeServiceImpl employeeService, SpareService spareService, SupplierService supplierService, CarService carService, OrderService orderService, ClientService clientService) {
        this.employeeService = employeeService;
        this.spareService = spareService;
        this.supplierService = supplierService;
        this.carService = carService;
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SERVICE_MANAGER", "ROLE_PARTS_SELLING_MANAGER"})
    @GetMapping("/spares")
    public List<Spare> getAvailableSpares() { //справочник запчастей
        return spareService.getAll();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_HR"})
    @GetMapping("/staff")
    public List<Employee> getStaffList() { //справочник сотрудников
        return employeeService.getEmployeeList();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SUPPLIER_D"})
    @GetMapping("/suppliers")
    public List<Supplier> getSuppliersList() { //справочник поставщиков овощей
        return supplierService.getAllSuppliers();
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_AUTO_SELLING_MANAGER"})
    @GetMapping("/cars")
    public List<Car> getDeliveredCars() { // справочник машин (таких как я прям, гы)
        return carService.getAllCars();
    }

    @Secured({"ROLE_SERVICE_MANAGER", "ROLE_MAINTENANCE_WORKER"})
    @GetMapping("/maintenance-orders")
    public List<MaintenanceOrder> getMaintenanceOrders() {
        return orderService.getToDoOrders();
    }

    @Secured({"ROLE_SERVICE_MANAGER", "ROLE_MAINTENANCE_WORKER"})
    @PostMapping("/order/done")
    public void markOrderAsDone(@RequestBody MaintenanceOrderDto orderDto){
        orderService.orderIsDone(orderDto);
    }

    @Secured("ROLE_DIRECTOR")
    @GetMapping("/add/spare")
    public void addSpareToList(@RequestBody SpareDto spareDto) {
        spareService.add(spareDto);
    }

    @Secured("ROLE_HR")
    @PostMapping("/add/employee")
    public void addEmployeeToList(@RequestBody EmployeeDto employeeDto) {
        employeeService.add(employeeDto);
    }

    @Secured("ROLE_SUPPLIER_D")
    @PostMapping("/add/supplier")
    public void addSupplierToList(@RequestBody SupplierDto supplierDto) {
        supplierService.add(supplierDto);
    }

    @Secured("ROLE_SUPPLIER_D")
    @PostMapping("/add/car")
    public void addCarToList(@RequestBody CarDto carDto) {
        carService.add(carDto);
    }

    @Secured("ROLE_SERVICE_MANAGER")
    @PostMapping("/add/maintenance-order")
    public void addMaintenanceOrderToList(@RequestBody MaintenanceOrderDto maintenanceOrderDto) {
        orderService.add(maintenanceOrderDto);
    }

    @Secured({"ROLE_SERVICE_MANAGER", "ROLE_AUTO_SELLING_MANAGER", "ROLE_PARTS_SELLING_MANAGER"})
    @PostMapping("/add/client")
    public void addClientToSystem(@RequestBody ClientDto clientDto){
        clientService.add(clientDto);
    }

    @Secured({"ROLE_DIRECTOR", "ROLE_SERVICE_MANAGER", "ROLE_AUTO_SELLING_MANAGER", "ROLE_PARTS_SELLING_MANAGER"})
    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientService.getClients();
    }
}
