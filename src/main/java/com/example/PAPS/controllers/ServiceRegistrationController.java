package com.example.PAPS.controllers;

import com.example.PAPS.dtos.ClientDto;
import com.example.PAPS.dtos.InsuranceDto;
import com.example.PAPS.dtos.MaintenanceOrderDto;
import com.example.PAPS.services.ClientService;
import com.example.PAPS.services.InsuranceService;
import com.example.PAPS.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceRegistrationController {

    private final InsuranceService insuranceService;
    private final ClientService clientService;

    public ServiceRegistrationController(InsuranceService insuranceService, ClientService clientService) {
        this.insuranceService = insuranceService;
        this.clientService = clientService;
    }

    @PostMapping("/insurance")
    public void registerInsurancePassport(@RequestBody InsuranceDto insuranceDto) {
        insuranceService.add(insuranceDto);
    }

    @PostMapping("/client")
    public void addClient(@RequestBody ClientDto clientDto) {
        clientService.add(clientDto);
    }
}
