package com.example.PAPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "vin", nullable = false, unique = true)
    private String VIN;

    @Column(name = "model", nullable = false)
    private String model;

    private String color; //тачка мб и не крашеная наверное, поэтому допускаю null'овый цвет

    private Date dateOfManufacture;

    private Double price;

    @OneToOne
    private Client owner;

    private Long ownerPassport;
}
