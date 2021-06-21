package com.example.PAPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "vin", nullable = false, unique = true)
    private String vin;

    @Column(name = "model", nullable = false)
    private String model;

    private String color; //тачка мб и не крашеная наверное, поэтому допускаю null'овый цвет

    private LocalDate dateOfManufacture;

    private Double price;

    @OneToOne
    private Client owner;

    private String ownerPassport;
}
