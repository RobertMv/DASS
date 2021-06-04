package com.example.PAPS.services;

import com.example.PAPS.dtos.CarDto;
import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.CarsReport;
import com.example.PAPS.repositories.CarRepository;
import com.example.PAPS.repositories.CarsReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarsReportRepository carsReportRepository;
    private final DtoEntityConversionService conversionService;

    public CarService(CarRepository carRepository, CarsReportRepository carsReportRepository, DtoEntityConversionService conversionService) {
        this.carRepository = carRepository;
        this.carsReportRepository = carsReportRepository;
        this.conversionService = conversionService;
    }

    public void add(CarDto carDto) {
        carRepository.save(conversionService.convert(carDto));
    }

    public void sell(CarDto carDto){
        Car car = carRepository.findCarByVin(carDto.getVIN());
        carRepository.delete(car);
        CarsReport report = new CarsReport();
        report.setModel(car.getModel());
        report.setDateOfManufacture(car.getDateOfManufacture());
        report.setDateOfSelling(LocalDate.now());
        report.setVIN(car.getVin());
        report.setColor(car.getColor());
        report.setPrice(car.getPrice());
        carsReportRepository.save(report);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }
}
