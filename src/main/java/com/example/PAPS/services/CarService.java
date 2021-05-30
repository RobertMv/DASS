package com.example.PAPS.services;

import com.example.PAPS.dtos.CarDto;
import com.example.PAPS.entities.Car;
import com.example.PAPS.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final DtoEntityConversionService conversionService;

    public CarService(CarRepository carRepository, DtoEntityConversionService conversionService) {
        this.carRepository = carRepository;
        this.conversionService = conversionService;
    }

    public void add(CarDto carDto) {
        carRepository.save(conversionService.convert(carDto));
    }

    public void sell(CarDto carDto){
        Car car = carRepository.findCarByVIN(carDto.getVIN());
        car.setAmount(car.getAmount()-1);
        carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
}
