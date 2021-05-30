package com.example.PAPS.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private Long id;
    private String VIN;
    private String model;
    private String color;
    private Date dateOfManufacture;
    private Double price;
    private String owner;
    private Integer amount;

}
