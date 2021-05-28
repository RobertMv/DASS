package com.example.PAPS.services;

import com.example.PAPS.dtos.*;
import com.example.PAPS.entities.*;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.ClientRepository;
import com.example.PAPS.repositories.MaintenanceOrderRepository;
import com.example.PAPS.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DtoEntityConversionService {

    private final SupplierRepository supplierRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final MaintenanceOrderRepository orderRepository;

    protected DtoEntityConversionService(SupplierRepository supplierRepository, CarRepository carRepository, ClientRepository clientRepository, MaintenanceOrderRepository orderRepository) {
        this.supplierRepository = supplierRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public Spare convert(SpareDto dto) { // from dto to entity
        Spare entity = new Spare();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setVendorCode(dto.getVendorCode());
        entity.setSupplier(supplierRepository.findSupplierByName(dto.getSupplier()));
        return entity;
    }

    public SpareDto convert(Spare entity) { // from entity to dto
        SpareDto dto = new SpareDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setVendorCode(entity.getVendorCode());
        dto.setSupplier(entity.getSupplier().getName());
        return dto;
    }

    public Employee convert(EmployeeDto dto) {
        Employee entity = new Employee();
        entity.setId(dto.getId());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setDateOfDismissal(dto.getDateOfDismissal());
        entity.setDateOfEmployment(dto.getDateOfEmployment());
        entity.setDepartmentCode(dto.getDepartmentCode());
        entity.setINN(dto.getINN());
        entity.setPassportID(dto.getPassportID());
        entity.setPatronymic(dto.getPatronymic());
        entity.setPhone(dto.getPhone());
        entity.setSurname(dto.getSurname());
        entity.setSex(dto.getSex());
        entity.setPositionCode(dto.getPositionCode());
        return entity;
    }

    public EmployeeDto convert(Employee entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setDateOfDismissal(entity.getDateOfDismissal());
        dto.setDateOfEmployment(entity.getDateOfEmployment());
        dto.setDepartmentCode(entity.getDepartmentCode());
        dto.setINN(entity.getINN());
        dto.setPassportID(entity.getPassportID());
        dto.setPatronymic(entity.getPatronymic());
        dto.setPhone(entity.getPhone());
        dto.setSurname(entity.getSurname());
        dto.setSex(entity.getSex());
        dto.setPositionCode(entity.getPositionCode());
        return dto;
    }

    public Insurance convert(InsuranceDto dto) {
        Insurance entity = new Insurance();
        entity.setId(dto.getId());
        entity.setCar(carRepository.findCarByVIN("VIN"));
        entity.setKind(dto.getKind());
        entity.setSeries(dto.getSeries());
        entity.setNumber(dto.getNumber());
        entity.setType(dto.getType());
        return entity;
    }

    public InsuranceDto convert(Insurance entity){
        return new InsuranceDto();
    }

    public Supplier convert(SupplierDto dto){
        Supplier entity = new Supplier();
        entity.setId(dto.getId());
        entity.setINN(dto.getINN());
        entity.setPhone(dto.getPhone());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setBankAccount(dto.getBankAccount());
        return entity;
    }

    public MaintenanceOrder convert(MaintenanceOrderDto dto){
        MaintenanceOrder entity = new MaintenanceOrder();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setCar(carRepository.findCarByVIN("VIN"));
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
