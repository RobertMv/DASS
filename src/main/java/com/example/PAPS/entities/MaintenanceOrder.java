package com.example.PAPS.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class MaintenanceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date dateOfMaintenance;

    @Column(nullable = false)
    private Double price;

    private String description;

    @Column(nullable = false)
    private String code;

    @OneToOne
    private Car car;
}
