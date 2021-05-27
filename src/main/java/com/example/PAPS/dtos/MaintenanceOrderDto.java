package com.example.PAPS.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceOrderDto {

    private Long id;
    private Date dateOfMaintenance;
    private String description, code, car;
    private Double price;
}
