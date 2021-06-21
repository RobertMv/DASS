package com.example.PAPS.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long id;
    private String VIN, model, color, owner;
    private LocalDate dateOfManufacture;
    private Double price;
    private String ownerPassport;
}
