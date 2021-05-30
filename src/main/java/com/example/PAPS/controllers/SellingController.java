package com.example.PAPS.controllers;

import com.example.PAPS.dtos.CarDto;
import com.example.PAPS.dtos.MaintenanceOrderDto;
import com.example.PAPS.dtos.SpareDto;
import com.example.PAPS.services.CarService;
import com.example.PAPS.services.OrderService;
import com.example.PAPS.services.SpareService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
public class SellingController {

    private final SpareService spareService;
    private final CarService carService;
    private final OrderService orderService;

    public SellingController(SpareService spareService, CarService carService, OrderService orderService) {
        this.spareService = spareService;
        this.carService = carService;
        this.orderService = orderService;
    }

    @Secured("ROLE_CAR_SELLING_MANAGER")
    @PostMapping("/car")
    public void sellCar(@RequestBody CarDto carDto) {
        carService.sell(carDto);
    }

    @Secured("ROLE_PARTS_SELLING_MANAGER")
    @PostMapping("/spare")
    public void sellSpare(@RequestBody SpareDto spareDto, Integer amount) {
        spareService.sell(spareDto, amount);
    }

    @Secured("ROLE_SERVICE_MANAGER")
    public void sellMaintenanceOrder(@RequestBody MaintenanceOrderDto orderDto){
        orderService.add(orderDto);
    }
}
