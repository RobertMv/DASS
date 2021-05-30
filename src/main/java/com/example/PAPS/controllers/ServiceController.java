package com.example.PAPS.controllers;

import com.example.PAPS.dtos.ClientDto;
import com.example.PAPS.dtos.InsuranceDto;
import com.example.PAPS.dtos.MaintenanceOrderDto;
import com.example.PAPS.services.ClientService;
import com.example.PAPS.services.InsuranceService;
import com.example.PAPS.services.OrderService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final InsuranceService insuranceService;

    public ServiceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @Secured("ROLE_SERVICE_MANAGER")
    @PostMapping("/insurance")
    public void registerInsurancePassport(@RequestBody InsuranceDto insuranceDto) {
        insuranceService.add(insuranceDto);
    }
}
