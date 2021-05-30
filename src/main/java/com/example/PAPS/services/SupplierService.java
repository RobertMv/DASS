package com.example.PAPS.services;

import com.example.PAPS.dtos.SupplierDto;
import com.example.PAPS.entities.Supplier;
import com.example.PAPS.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final DtoEntityConversionService dtoEntityConversionService;

    public SupplierService(SupplierRepository supplierRepository, DtoEntityConversionService dtoEntityConversionService) {
        this.supplierRepository = supplierRepository;
        this.dtoEntityConversionService = dtoEntityConversionService;
    }

    public void add(SupplierDto supplierDto) {
        supplierRepository.save(dtoEntityConversionService.convert(supplierDto));
    }

    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }
}
