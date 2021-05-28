package com.example.PAPS.services;

import com.example.PAPS.dtos.CarDto;
import com.example.PAPS.entities.Car;
import com.example.PAPS.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void add(CarDto carDto) {
    }

    public void sell(CarDto carDto){

    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
}
