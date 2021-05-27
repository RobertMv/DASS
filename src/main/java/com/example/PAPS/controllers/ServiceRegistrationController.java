package com.example.PAPS.controllers;

import com.example.PAPS.dtos.ClientDto;
import com.example.PAPS.dtos.InsuranceDto;
import com.example.PAPS.dtos.MaintenanceOrderDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceRegistrationController {


    @PostMapping("/maintenance")
    public void registerMaintenance(@RequestBody MaintenanceOrderDto maintenanceOrderDto){
        //maintenanceService.register(maintenanceOrderDto);
    }

    @PostMapping("/insurance")
    public void registerInsurancePassport(@RequestBody InsuranceDto insuranceDto){
        //insuranceService.register(insuranceDto);
    }

    @PostMapping("/client")
    public void addClient(@RequestBody ClientDto clientDto){
        //clientService.add(clientDto);
    }
}
