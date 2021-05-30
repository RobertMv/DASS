package com.example.PAPS.services;

import com.example.PAPS.dtos.InsuranceDto;
import com.example.PAPS.repositories.InsuranceRepository;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final DtoEntityConversionService conversionService;

    public InsuranceService(InsuranceRepository insuranceRepository, DtoEntityConversionService conversionService) {
        this.insuranceRepository = insuranceRepository;
        this.conversionService = conversionService;
    }

    public void add(InsuranceDto insuranceDto){
        insuranceRepository.save(conversionService.convert(insuranceDto));
    }
}
