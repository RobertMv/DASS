package com.example.PAPS.services;

import com.example.PAPS.dtos.*;
import com.example.PAPS.entities.*;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.ClientRepository;
import com.example.PAPS.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class DtoEntityConversionService {

    private final SupplierRepository supplierRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    protected DtoEntityConversionService(SupplierRepository supplierRepository, CarRepository carRepository, ClientRepository clientRepository) {
        this.supplierRepository = supplierRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
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
        entity.setCar(carRepository.findCarByVin(dto.getCar()));
        entity.setKind(dto.getKind());
        entity.setSeries(dto.getSeries());
        entity.setNumber(dto.getNumber());
        entity.setType(dto.getType());
        return entity;
    }

    public InsuranceDto convert(Insurance entity) {
        InsuranceDto dto = new InsuranceDto();
        dto.setId(entity.getId());
        dto.setCar(entity.getCar().getVin());
        dto.setSeries(entity.getSeries());
        dto.setKind(entity.getKind());
        dto.setType(entity.getType());
        dto.setNumber(entity.getNumber());
        return dto;
    }

    public Supplier convert(SupplierDto dto) {
        Supplier entity = new Supplier();
        entity.setId(dto.getId());
        entity.setINN(dto.getINN());
        entity.setPhone(dto.getPhone());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setBankAccount(dto.getBankAccount());
        return entity;
    }

    public SupplierDto convert(Supplier entity) {
        SupplierDto dto = new SupplierDto();
        dto.setId(entity.getId());
        dto.setINN(entity.getINN());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setBankAccount(entity.getBankAccount());
        return dto;
    }

    public MaintenanceOrder convert(MaintenanceOrderDto dto) {
        MaintenanceOrder entity = new MaintenanceOrder();
        entity.setId(dto.getId());
        entity.setCar(carRepository.findCarByVin(dto.getCar()));
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public MaintenanceOrderDto convert(MaintenanceOrder entity) {
        MaintenanceOrderDto dto = new MaintenanceOrderDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setCar(entity.getCar().getVin());
        dto.setPrice(entity.getPrice());
        dto.setDateOfMaintenance(entity.getDateOfMaintenance());
        return dto;
    }

    public Client convert(ClientDto dto) {
        Client entity = new Client();
        entity.setId(dto.getId());
        entity.setAddress(dto.getAddress());
        entity.setPassport(dto.getPassport());
        entity.setFio(dto.getFio());
        return entity;
    }

    public ClientDto convert(Client entity) {
        ClientDto dto = new ClientDto();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setPassport(entity.getPassport());
        dto.setFio(entity.getFio());
        return dto;
    }

    public Car convert(CarDto dto) {
        Car entity = new Car();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setColor(dto.getColor());
        entity.setModel(dto.getModel());
        entity.setDateOfManufacture(dto.getDateOfManufacture());
        entity.setOwner(clientRepository.findClientByPassport(dto.getOwnerPassport()));
        entity.setVin(dto.getVIN());
        return entity;
    }

    public CarDto convert(Car entity) {
        CarDto dto = new CarDto();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setColor(entity.getColor());
        dto.setModel(entity.getModel());
        dto.setDateOfManufacture(entity.getDateOfManufacture());
        dto.setOwner(entity.getOwner().getFio());
        dto.setOwnerPassport(entity.getOwnerPassport());
        dto.setVIN(entity.getVin());
        return dto;
    }
}
