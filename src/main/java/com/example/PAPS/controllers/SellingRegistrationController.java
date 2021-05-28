package com.example.PAPS.controllers;

import com.example.PAPS.dtos.CarDto;
import com.example.PAPS.dtos.ClientDto;
import com.example.PAPS.dtos.SpareDto;
import com.example.PAPS.services.CarService;
import com.example.PAPS.services.ClientService;
import com.example.PAPS.services.SpareService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
public class SellingRegistrationController {

    private final SpareService spareService;
    private final CarService carService;
    private final ClientService clientService;

    public SellingRegistrationController(SpareService spareService, CarService carService, ClientService clientService) {
        this.spareService = spareService;
        this.carService = carService;
        this.clientService = clientService;
    }

    @PostMapping("/car")
    public void sellCar(@RequestBody CarDto carDto) {
        carService.sell(carDto);
    }

    @PostMapping("/spare")
    public void sellSpare(@RequestBody SpareDto spareDto, Integer amount) {
        spareService.sell(spareDto, amount);
    }

    @PostMapping("/add/client")
    public void addClient(@RequestBody ClientDto clientDto) {
        clientService.add(clientDto);
    }
}
