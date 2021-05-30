package com.example.PAPS.repositories;

import com.example.PAPS.entities.Car;
import com.example.PAPS.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findCarByVIN(String vin);
    Car findCarByOwnerAndModel(Client owner, String Model);
}
