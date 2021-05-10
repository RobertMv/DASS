package com.example.PAPS.controllers;

import com.example.PAPS.dtos.CarDto;
import com.example.PAPS.dtos.ClientDto;
import com.example.PAPS.dtos.SpareDto;
import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.Spare;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
public class SellingRegistrationController {

    @PostMapping("/car")
    public void sellCar(@RequestBody CarDto carDto){
        //carService.sell(carDto);
    }

    @PostMapping("/spare")
    public void sellSpare(@RequestBody SpareDto spareDto){
        //spareService.sell(spareDto)
    }

    @PostMapping("/add/client")
    public void addClient(@RequestBody ClientDto clientDto){
        //clientService.add(clientDto);
    }
}
