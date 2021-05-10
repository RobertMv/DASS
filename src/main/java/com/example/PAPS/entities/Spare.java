package com.example.PAPS.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Spare {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String code;

    private Long vendorCode;

    @ManyToOne
    private Supplier supplier;
}
